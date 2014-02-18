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
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package gov.va.isaac.interfaces.gui.views;

import gov.va.isaac.interfaces.gui.MenuItemI;
import java.util.List;
import org.jvnet.hk2.annotations.Contract;

/**
 * IsaacViewI
 * 
 * The lowest level of a View - only provides menu options.  No view display hooks.
 *
 * @author <a href="mailto:daniel.armbrust.list@gmail.com">Dan Armbrust</a> 
 */
@Contract
public abstract interface IsaacViewI
{
	/**
	 * Provides the specs of all of the menus required by this view.  May return an empty list, will not return null.
	 */
	public List<MenuItemI> getMenuBarMenus();
	
}