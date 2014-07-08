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
package gov.va.isaac.workflow.gui;

import gov.va.isaac.AppContext;
import gov.va.isaac.ExtendedAppContext;
import gov.va.isaac.interfaces.gui.ApplicationWindowI;
import gov.va.isaac.interfaces.gui.views.DockedViewI;
import gov.va.isaac.interfaces.utility.ShutdownBroadcastListenerI;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;

import javax.validation.ValidationException;

import javafx.application.Application;
import javafx.stage.Stage;

import org.ihtsdo.otf.query.lucene.LuceneIndexer;
import org.ihtsdo.otf.tcc.api.metadata.binding.Snomed;
import org.ihtsdo.otf.tcc.datastore.BdbTerminologyStore;
import org.ihtsdo.otf.tcc.lookup.Hk2Looker;
import org.jvnet.hk2.annotations.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * {@link ConceptDetailWorkflowRunner}
 * 
 * @author <a href="mailto:daniel.armbrust.list@gmail.com">Dan Armbrust</a>
 */
@Service
public class ConceptDetailWorkflowRunner extends Application
{
	private final Logger logger = LoggerFactory.getLogger(ConceptDetailWorkflowRunner.class);

	Stage primaryStage_;
	/**
	 * @see javafx.application.Application#start(javafx.stage.Stage)
	 */
	@Override
	public void start(Stage primaryStage) throws Exception
	{
		ConceptDetailWorkflow lv = AppContext.getService(ConceptDetailWorkflow.class);
		primaryStage_ = lv;
		int nid = 0;
		// This code is a hack to populate the conceptView with a test concept
		try {
			// NOTE: this call requires DB
			nid = Snomed.ORGANISM.getNid();
			lv.setConcept(nid);
		} catch (ValidationException e1) {
			logger.error("Error: getting Nid from concept " + Snomed.ORGANISM + ": caught " + e1.getClass().getName() + " \"" + e1.getLocalizedMessage() + "\"");
			e1.printStackTrace();
		} catch (IOException e1) {
			logger.error("Error: getting Nid from concept " + Snomed.ORGANISM + ": caught " + e1.getClass().getName() + " \"" + e1.getLocalizedMessage() + "\"");
			e1.printStackTrace();
		}
		lv.show();
	}

	/**
	 * @param args
	 * @throws IOException
	 * @throws ClassNotFoundException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws SecurityException
	 * @throws NoSuchFieldException
	 */
	public static void main(String[] args) throws ClassNotFoundException, IOException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException,
			SecurityException
	{
		AppContext.setup();
		// TODO OTF fix: this needs to be fixed so I don't have to hack it with reflection.... https://jira.ihtsdotools.org/browse/OTFISSUE-11
		Field f = Hk2Looker.class.getDeclaredField("looker");
		f.setAccessible(true);
		f.set(null, AppContext.getServiceLocator());
		System.setProperty(BdbTerminologyStore.BDB_LOCATION_PROPERTY, new File("../../ISAAC-PA/app/berkeley-db").getCanonicalPath());
		System.setProperty(LuceneIndexer.LUCENE_ROOT_LOCATION_PROPERTY, new File("../../ISAAC-PA/app/berkeley-db").getCanonicalPath());
		launch(args);
	}
}