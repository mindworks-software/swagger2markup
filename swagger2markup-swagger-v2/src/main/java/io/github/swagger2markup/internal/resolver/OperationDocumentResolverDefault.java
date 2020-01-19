/*
 * Copyright 2017 Robert Winkler
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.github.swagger2markup.internal.resolver;

import io.github.swagger2markup.Swagger2MarkupConverter;
import io.github.swagger2markup.model.PathOperation;

import static org.apache.commons.lang3.StringUtils.defaultString;

/**
 * Default {@code DocumentResolver} functor
 */
public class OperationDocumentResolverDefault extends OperationDocumentResolver {

    private final OperationDocumentNameResolver operationDocumentNameResolver;

    public OperationDocumentResolverDefault(Swagger2MarkupConverter.SwaggerContext context) {
        super(context);
        this.operationDocumentNameResolver = new OperationDocumentNameResolver(context);
    }

    public String apply(PathOperation operation) {
        if (!config.isInterDocumentCrossReferencesEnabled() || context.getOutputPath() == null)
            return null;
        else
            return defaultString(config.getInterDocumentCrossReferencesPrefix()) + operationDocumentNameResolver.apply(operation);
    }
}