/*
 *  Licensed to the Apache Software Foundation (ASF) under one
 *  or more contributor license agreements.  See the NOTICE file
 *  distributed with this work for additional information
 *  regarding copyright ownership.  The ASF licenses this file
 *  to you under the Apache License, Version 2.0 (the
 *  "License"); you may not use this file except in compliance
 *  with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License.
 */

package org.apache.gravitino.lineage.sink;

import io.openlineage.server.OpenLineage.RunEvent;
import java.io.Closeable;
import java.util.List;
import java.util.Map;

@SuppressWarnings("unused")
public class LineageSinkManager implements Closeable {

  public void initialize(List<String> sinks, Map<String, String> LineageConfigs) {}

  // Checks if the sink queue size surpasses the threshold to avoid overwhelming lineage sinks.
  public boolean isHighWatermark() {
    return false;
  }

  public void sink(RunEvent runEvent) {}

  @Override
  public void close() {}
}
