/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.apache.gravitino.cli.commands;

import com.google.common.base.Joiner;
import java.util.Collections;
import org.apache.gravitino.cli.CommandContext;
import org.apache.gravitino.cli.ErrorMessages;
import org.apache.gravitino.client.GravitinoClient;
import org.apache.gravitino.exceptions.NoSuchMetalakeException;
import org.apache.gravitino.exceptions.RoleAlreadyExistsException;

public class CreateRole extends Command {
  protected String metalake;
  protected String[] roles;

  /**
   * Create a new role.
   *
   * @param context The command context.
   * @param metalake The name of the metalake.
   * @param roles The array of roles.
   */
  public CreateRole(CommandContext context, String metalake, String[] roles) {
    super(context);
    this.metalake = metalake;
    this.roles = roles;
  }

  /** Create a new role. */
  @Override
  public void handle() {
    try {
      GravitinoClient client = buildClient(metalake);
      for (String role : roles) {
        client.createRole(role, null, Collections.EMPTY_LIST);
      }
    } catch (NoSuchMetalakeException err) {
      exitWithError(ErrorMessages.UNKNOWN_METALAKE);
    } catch (RoleAlreadyExistsException err) {
      exitWithError(ErrorMessages.ROLE_EXISTS);
    } catch (Exception exp) {
      exitWithError(exp.getMessage());
    }

    printInformation(Joiner.on(", ").join(roles) + " created");
  }
}
