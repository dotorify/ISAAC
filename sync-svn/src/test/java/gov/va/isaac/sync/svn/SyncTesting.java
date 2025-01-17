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
package gov.va.isaac.sync.svn;

import gov.va.isaac.AppContext;
import gov.va.isaac.interfaces.sync.MergeFailOption;
import gov.va.isaac.interfaces.sync.ProfileSyncI;
import java.io.File;
import org.slf4j.bridge.SLF4JBridgeHandler;

/**
 * {@link SyncTesting}
 *
 * @author <a href="mailto:daniel.armbrust.list@gmail.com">Dan Armbrust</a>
 */
public class SyncTesting
{
	public static void main(String[] args) throws Exception
	{
		SLF4JBridgeHandler.removeHandlersForRootLogger();
		SLF4JBridgeHandler.install();
		AppContext.setup();
		ProfileSyncI ssg = AppContext.getService(SyncServiceSVN.class);
		File localFolder = new File("/mnt/SSD/scratch/svnTest1/svnTestRepo");
		ssg.setRootLocation(localFolder);

		String username = "username";
		String password = "password";
		
		ssg.linkAndFetchFromRemote("https://csfe.aceworkspace.net/svn/repos/isaac_pa_demo_changesets_svn", username, password);
		

		System.out.println(ssg.getFilesInMergeConflict());
		System.out.println(ssg.getLocallyModifiedFileCount());
		//ssg.addFiles("foo3");
		//ssg.removeFiles("b");
		//ssg.addUntrackedFiles();
//		ssg.relinkRemote("file:///mnt/SSD/scratch/svnTestRepo2");
//		System.out.println(ssg.updateFromRemote(username, password, MergeFailOption.KEEP_LOCAL));
//		HashMap<String, MergeFailOption> resolutions = new HashMap<>();
//		resolutions.put("a", MergeFailOption.KEEP_REMOTE);
//		System.out.println(ssg.resolveMergeFailures(resolutions));
		ssg.addUntrackedFiles();
		System.out.println(ssg.updateCommitAndPush("test message", username, password, MergeFailOption.FAIL,(String[])null));
		
//		ssg.linkAndFetchFromRemote("file:///mnt/SSD/scratch/svnTestRepo2", username, password);
		
	}
}
