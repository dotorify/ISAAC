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
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package gov.va.issac.drools.evaluators.facts;

import org.ihtsdo.otf.tcc.api.chronicle.ComponentVersionBI;
import org.ihtsdo.otf.tcc.api.coordinate.ViewCoordinate;

/**
 * 
 * {@link ComponentFact}
 *
 * @author kec
 * @author afurber
 * @author <a href="mailto:daniel.armbrust.list@gmail.com">Dan Armbrust</a> 
 */
public class ComponentFact <T extends ComponentVersionBI> extends Fact<T>{
	
	private ViewCoordinate vc;
	
	protected ComponentFact(Context context, T component, ViewCoordinate vc) {
		super(context, component);
		this.vc = vc;
	}
	
	public ViewCoordinate getVc() {
		return vc;
	}

	@Override
	public String toString() {
		return "Fact context: " + context + " component: " + component;
	}

}