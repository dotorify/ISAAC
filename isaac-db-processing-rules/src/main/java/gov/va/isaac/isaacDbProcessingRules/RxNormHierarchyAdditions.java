/**
 * Copyright Notice
 *
 * This is a work of the U.S. Government and is not subject to copyright
 * protection in the United States. Foreign copyrights may apply.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package gov.va.isaac.isaacDbProcessingRules;

import gov.va.isaac.mojos.dbTransforms.TransformI;
import gov.va.isaac.util.OTFUtility;
import java.io.File;
import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import javax.inject.Named;
import org.ihtsdo.otf.tcc.api.blueprint.IdDirective;
import org.ihtsdo.otf.tcc.api.blueprint.RelationshipCAB;
import org.ihtsdo.otf.tcc.api.concept.ConceptChronicleBI;
import org.ihtsdo.otf.tcc.api.concept.ConceptFetcherBI;
import org.ihtsdo.otf.tcc.api.concept.ProcessUnfetchedConceptDataBI;
import org.ihtsdo.otf.tcc.api.coordinate.EditCoordinate;
import org.ihtsdo.otf.tcc.api.coordinate.StandardViewCoordinates;
import org.ihtsdo.otf.tcc.api.description.DescriptionChronicleBI;
import org.ihtsdo.otf.tcc.api.description.DescriptionVersionBI;
import org.ihtsdo.otf.tcc.api.metadata.binding.Snomed;
import org.ihtsdo.otf.tcc.api.metadata.binding.TermAux;
import org.ihtsdo.otf.tcc.api.nid.NativeIdSetBI;
import org.ihtsdo.otf.tcc.api.refex.RefexChronicleBI;
import org.ihtsdo.otf.tcc.api.refex.RefexVersionBI;
import org.ihtsdo.otf.tcc.api.refex.type_nid.RefexNidVersionBI;
import org.ihtsdo.otf.tcc.api.relationship.RelationshipType;
import org.ihtsdo.otf.tcc.api.store.TerminologyStoreDI;
import org.jvnet.hk2.annotations.Service;

/**
 * {@link RxNormHierarchyAdditions}
 * 
 * A Transformer that checks all concepts in the DB, looking to see if each
 * concept has a description type of preferred. If a concept does not have a
 * description type of preferred - it creates a new description from the FSN
 * (stripping out the semantic tag, if present.
 * 
 * Logs an error if no FSN is found on a concept.
 * 
 *
 * @author <a href="mailto:daniel.armbrust.list@gmail.com">Dan Armbrust</a>
 */
@Service
@Named(value = "RxNorm Hierarchy Additions")
public class RxNormHierarchyAdditions implements TransformI
{
	private AtomicInteger addedRels = new AtomicInteger();
	private AtomicInteger examinedConcepts = new AtomicInteger();
	
	private final UUID penicillinProduct = UUID.fromString("fdca98cf-8720-3dbe-bb72-3377d658a85c");//Penicillin -class of antibiotic- (product)
	private final UUID termTypeIN = UUID.fromString("17114d54-ed48-5f0a-a865-4ecec3e31cdc"); //Name for an Ingredient     // IN
	private final UUID rxNormHPath = UUID.fromString("763c21ad-55e3-5bb3-af1e-3e4fb475de44"); //RxNormH Path

	/**
	 * @see gov.va.isaac.mojos.dbTransforms.TransformI#getName()
	 */
	@Override
	public String getName()
	{
		return "RxNorm Hierarchy Additions";
	}
	
	/**
	 * @see gov.va.isaac.mojos.dbTransforms.TransformI#configure(java.io.File)
	 */
	@Override
	public void configure(File configFile)
	{
		// noop
	}

	/**
	 * @throws Exception
	 * @see gov.va.isaac.mojos.dbTransforms.TransformI#transform(org.ihtsdo.otf.tcc.api.store.TerminologyStoreDI)
	 */
	@Override
	public void transform(TerminologyStoreDI ts) throws Exception
	{
		int ttyNid = ts.getNidForUuids(termTypeIN);
		int rxNormPathNid = ts.getNidForUuids(rxNormHPath);
		ts.iterateConceptDataInParallel(new ProcessUnfetchedConceptDataBI()
		{
			@Override
			public boolean continueWork()
			{
				return true;
			}

			@Override
			public void processUnfetchedConceptData(int cNid, ConceptFetcherBI fetcher) throws Exception
			{
				ConceptChronicleBI cc = fetcher.fetch();
				examinedConcepts.getAndIncrement();
				
				boolean isRxNormPath = false;
				for (int stampNid : cc.getConceptAttributes().getAllStamps())
				{
					if (ts.getPathNidForStamp(stampNid) == rxNormPathNid)
					{
						isRxNormPath = true;
						break;
					}
				}
				
				if (!isRxNormPath)
				{
					return;
				}
				
				boolean isRxNormIN = false;
				boolean hasPenicillin = false;
				for (DescriptionChronicleBI desc : cc.getDescriptions())
				{
					if (hasPenicillin && isRxNormIN)
					{
						break;
					}
					DescriptionVersionBI<?> currentDescription = OTFUtility.getLatestDescVersion(desc.getVersions());
					
					if (currentDescription == null)
					{
						continue;
					}
					
					for (RefexChronicleBI<?> refex : currentDescription.getRefexes())
					{
						RefexVersionBI<?> currentRefex = OTFUtility.getLatestRefexVersion(refex.getVersions());
						if (currentRefex instanceof RefexNidVersionBI)
						{
							if (((RefexNidVersionBI<?>)currentRefex).getNid1() == ttyNid)
							{
								isRxNormIN = true;
								break;
							}
						}
					}
					
					if (currentDescription.getText().toLowerCase().contains("penicillin"))
					{
						hasPenicillin = true;
					}
				}
				
				if (hasPenicillin && isRxNormIN)
				{
					RelationshipCAB rCab = new RelationshipCAB(cc.getPrimordialUuid(), Snomed.IS_A.getUuids()[0], penicillinProduct,
							0, RelationshipType.STATED_ROLE, IdDirective.GENERATE_HASH);
					
					ts.getTerminologyBuilder(new EditCoordinate(TermAux.USER.getLenient().getConceptNid(), TermAux.TERM_AUX_MODULE.getLenient().getNid(), 
							TermAux.WB_AUX_PATH.getLenient().getConceptNid()), StandardViewCoordinates.getWbAuxiliary()).construct(rCab);
					ts.addUncommitted(cc);
					
					int last = addedRels.getAndIncrement();
					if (last % 2000 == 0)
					{
						ts.commit();
					}
				}
			}

			@Override
			public String getTitle()
			{
				return "RxNorm Hierachy Creator";
			}

			@Override
			public NativeIdSetBI getNidSet() throws IOException
			{
				return null;
			}

			@Override
			public boolean allowCancel()
			{
				return false;
			}
		});
		ts.commit();
	}

	/**
	 * @see gov.va.isaac.mojos.dbTransforms.TransformI#getDescription()
	 */
	@Override
	public String getDescription()
	{
		return "Add various relationships between RxNorm and Snomed CT";
	}

	/**
	 * @see gov.va.isaac.mojos.dbTransforms.TransformI#getWorkResultSummary()
	 */
	@Override
	public String getWorkResultSummary()
	{
		return "Examined " + examinedConcepts.get() + " concepts and generated " + addedRels.get() + " new relationships";
	}
}
