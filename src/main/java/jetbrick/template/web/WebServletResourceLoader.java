/**
 * Copyright 2013-2014 Guoqiang Chen, Shanghai, China. All rights reserved.
 *
 *   Author: Guoqiang Chen
 *    Email: subchen@gmail.com
 *   WebURL: https://github.com/subchen
 *
 * Licensed under the Apache License, Version 2.0 (the License);
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an AS IS BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package jetbrick.template.web;

import javax.servlet.*;
import jetbrick.io.resource.ServletResource;
import jetbrick.template.resource.loader.*;
import jetbrick.template.JetEngine;
import jetbrick.template.resource.*;
import jetbrick.util.PathUtils;

public final class WebServletResourceLoader implements ResourceLoader {
    private String root = "/";

    public void setRoot(String root) {
        this.root = PathUtils.separatorsToSystem(root);
    }

    @Override
    public Resource load(String name) {
        String path = PathUtils.concat(root, name);

        ServletContext sc = JetEngineLoader.getServletContext();
        ServletResource resource = new ServletResource(sc, path);
        if (!resource.exist()) {
            return null;
        }

        return new TemplateResource(resource, name);
    }
}