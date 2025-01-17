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
package gov.va.isaac.interfaces.gui.views.commonFunctionality;

import org.jvnet.hk2.annotations.Contract;

/**
 * ListBatchViewI
 * 
 * @author <a href="mailto:joel.kniaz@gmail.com">Joel Kniaz</a>
 */


import gov.va.isaac.interfaces.gui.views.EmbeddableViewI;
import java.util.List;

/**
 * {@link ListBatchViewI}
 * 
 * An interface that allows the creation of an ListBatchViewI implementation, which 
 * will be a JavaFX component that extends {@link EmbeddableViewI}.  The resulting ISAAC panel
 * is intended to allow importation into, display and manipulation of concepts to and
 * from other ISAAC panels
 *
 * @author <a href="jkniaz@apelon.com">Joel Kniaz</a>
 */
@Contract
public interface ListBatchViewI extends EmbeddableViewI {
	/**
	 * Update the view to show the specified concepts
	 * @param List<Integer> the list of specified concepts as int NID
	 */
	public void addConcepts(List<Integer> nids);

	/**
	 * Update the view to show the specified concept
	 * @param int the specified concept as int NID
	 */
	public void addConcept(int nid);
}