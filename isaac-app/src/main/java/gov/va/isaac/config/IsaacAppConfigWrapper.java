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
package gov.va.isaac.config;

import java.io.InputStream;
import javax.inject.Singleton;
import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import org.jvnet.hk2.annotations.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import gov.va.isaac.AppContext;
import gov.va.isaac.config.generated.IsaacAppConfig;
import gov.va.isaac.interfaces.config.IsaacAppConfigI;

/**
 * {@link IsaacAppConfigWrapper}
 * 
 * An ugly hack to make the generated (@link IsaacAppConfig} class implement the {@link IsaacAppConfigI}
 * But it also gives us a place to set up some defaults, if the user provided data was unreadable / missing.
 *
 * @author <a href="mailto:daniel.armbrust.list@gmail.com">Dan Armbrust</a> 
 */
@Service
@Singleton
public class IsaacAppConfigWrapper extends IsaacAppConfig implements IsaacAppConfigI
{
	private static Logger log_ = LoggerFactory.getLogger(IsaacAppConfigWrapper.class);
	
	public IsaacAppConfigWrapper()
	{
		//Default values
		setApplicationTitle("Default (unbranded) ISAAC Application");
		
		try
		{
			InputStream in = AppContext.class.getResourceAsStream("/app.xml");
			if (in != null)
			{
				JAXBContext jaxbContext = JAXBContext.newInstance(IsaacAppConfig.class);
				SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
				Schema schema = factory.newSchema(new StreamSource(AppContext.class.getResourceAsStream("/xsd/AppConfigSchema.xsd")));
				Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
				jaxbUnmarshaller.setSchema(schema);
				IsaacAppConfig temp = (IsaacAppConfig) jaxbUnmarshaller.unmarshal(in);
				copyHack(temp);
				in.close();
			}
			else
			{
				log_.warn("App configuration file not found, using defaults");
			}

		}
		catch (Exception ex)
		{
			log_.warn("Unexpected error reading app configuration file, using defaults", ex);
		}
	}
	
	private void copyHack(IsaacAppConfig read)
	{
		setApplicationTitle(read.getApplicationTitle());
		setUserRepositoryPath(getUserRepositoryPath());
	}
}