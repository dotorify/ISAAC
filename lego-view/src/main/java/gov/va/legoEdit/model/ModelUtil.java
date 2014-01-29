/**
 * Copyright 2013
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
package gov.va.legoEdit.model;

import gov.va.legoEdit.model.schemaModel.Lego;

/**
 * 
 * ModelUtil
 * 
 * @author <a href="mailto:daniel.armbrust.list@gmail.com">Dan Armbrust</a>
 * Copyright 2013
 */
public class ModelUtil
{
	public static String makeUniqueLegoID(Lego lego)
	{
		return makeUniqueLegoID(lego.getLegoUUID(), lego.getStamp().getUuid());
	}

	public static String makeUniqueLegoID(String legoUUID, String stampUUID)
	{
		return legoUUID + ":" + stampUUID;
	}
}
