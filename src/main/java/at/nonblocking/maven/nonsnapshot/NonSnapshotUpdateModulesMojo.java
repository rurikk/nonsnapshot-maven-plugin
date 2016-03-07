/*
 * Copyright 2012-2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package at.nonblocking.maven.nonsnapshot;

import at.nonblocking.maven.nonsnapshot.model.MavenModule;
import at.nonblocking.maven.nonsnapshot.model.UpdatedUpstreamMavenArtifact;
import org.apache.maven.plugins.annotations.Mojo;

import java.util.List;

@Mojo(name = "updateModules", aggregator = true)
public class NonSnapshotUpdateModulesMojo extends NonSnapshotUpdateVersionsMojo {

    @Override
    protected void updateUpstreamArtifacts(List<MavenModule> mavenModules) {
        for (MavenModule mavenModule : mavenModules) {
            if (mavenModule.getParent() != null) {
                UpdatedUpstreamMavenArtifact updatedUpstreamMavenArtifactParent = updateUpstreamArtifact(mavenModule.getParent());
                if (updatedUpstreamMavenArtifactParent != null) {
                    mavenModule.setParent(updatedUpstreamMavenArtifactParent);
                }
            }
        }
    }
}
