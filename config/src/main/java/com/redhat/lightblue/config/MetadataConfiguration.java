/*
 Copyright 2013 Red Hat, Inc. and/or its affiliates.

 This file is part of lightblue.

 This program is free software: you can redistribute it and/or modify
 it under the terms of the GNU General Public License as published by
 the Free Software Foundation, either version 3 of the License, or
 (at your option) any later version.

 This program is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 GNU General Public License for more details.

 You should have received a copy of the GNU General Public License
 along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.redhat.lightblue.config;

import com.redhat.lightblue.metadata.Metadata;
import com.redhat.lightblue.metadata.parser.HookConfigurationParser;
import com.redhat.lightblue.metadata.parser.JSONMetadataParser;
import com.redhat.lightblue.util.JsonInitializable;

import java.util.List;

public interface MetadataConfiguration extends JsonInitializable {
    /**
     * The file on classpath that this configuration is loaded from.
     */
    public static final String FILENAME = "lightblue-metadata.json";

    /**
     * Returns whether or not to validate metadata requests
     */
    boolean isValidateRequests();

    /**
     * Creates an instance of metadata
     *
     * @param ds Datasources
     * @param parser The JSON parser instance for metadata
     * @param mgr The factory instance creating metadata
     */
    Metadata createMetadata(DataSourcesConfiguration ds, JSONMetadataParser parser, LightblueFactory mgr);

    /**
     * returns the list of configured hooks
     */
    List<HookConfigurationParser> getHookConfigurationParsers();
}
