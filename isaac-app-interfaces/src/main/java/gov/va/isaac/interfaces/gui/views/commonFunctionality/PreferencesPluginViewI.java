package gov.va.isaac.interfaces.gui.views.commonFunctionality;
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

import java.io.IOException;
import javafx.beans.property.ReadOnlyStringProperty;
import javafx.scene.layout.Region;
import org.jvnet.hk2.annotations.Contract;

/**
 * PreferencesPluginViewI
 * 
 * @author <a href="mailto:joel.kniaz@gmail.com">Joel Kniaz</a>
 *
 */
@Contract
public interface PreferencesPluginViewI {
	String getName();
	ReadOnlyStringProperty validationFailureMessageProperty();
	Region getContent();
	void save() throws IOException;

	default int getTabOrder() {
		return Integer.MAX_VALUE;
	}
}
