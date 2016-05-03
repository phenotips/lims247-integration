/*
 * See the NOTICE file distributed with this work for additional
 * information regarding copyright ownership.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see http://www.gnu.org/licenses/
 */
package org.phenotips.integration.lims247;

import org.xwiki.component.annotation.Role;
import org.xwiki.stability.Unstable;

import net.sf.json.JSONObject;

/**
 * Communication with a LIMS server.
 *
 * @version $Id$
 * @since 1.0M8
 */
@Unstable
@Role
public interface LimsServer
{
    /** Parameter name used for the LIMS instance identifier. */
    String INSTANCE_IDENTIFIER_KEY = "pn";

    /** Parameter name used for the username. */
    String USERNAME_KEY = "username";

    /** Parameter name used for the authentication token. */
    String TOKEN_KEY = "auth_token";

    /** Parameter name used to specify the access level granted, view or edit. */
    String ACCESS_MODE = "access";

    /**
     * Check if the authentication parameters are validated by the remote LIMS server.
     *
     * @param token the authentication token to check
     * @param username the target username
     * @param pn the LIMS instance identifier
     * @return a valid user if the authentication is validated, or {@code null} in case of error: unknown instance
     *         identifier, invalid token
     */
    boolean checkToken(String token, String username, String pn);

    /**
     * Notify a remote LIMS instance that a patient's phenotype has changed.
     *
     * @param payload a JSON containing values for {@code pn} (the PhenoTips instance identifier), {@code username} (the
     *            username that performed the change), {@code auth_token} (the authentication token, either the one used
     *            by LIMS if the authentication originated on LIMS, or the one used by PhenoTips if the authentication
     *            originated on PhenoTips), {@code event} (one of {@code create}, {@code update} or {@code delete}), and
     *            {@code eid} (the patient's identifier, common between the two servers)
     * @param pn the LIMS server instance identifier
     */
    void notify(JSONObject payload, String pn);
}
