/**
 * Copyright (c) 2010-2023 Contributors to the openHAB project
 *
 * See the NOTICE file(s) distributed with this work for additional
 * information.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 *
 * SPDX-License-Identifier: EPL-2.0
 */
package org.openhab.binding.connectedcar.internal.api.carnet;

import static org.openhab.binding.connectedcar.internal.api.carnet.CarNetApiGSonDTO.*;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;
import org.openhab.binding.connectedcar.internal.api.ApiBrandProperties;
import org.openhab.binding.connectedcar.internal.api.ApiEventListener;
import org.openhab.binding.connectedcar.internal.api.ApiHttpClient;
import org.openhab.binding.connectedcar.internal.api.BrandAuthenticator;
import org.openhab.binding.connectedcar.internal.api.IdentityManager;
import org.openhab.binding.connectedcar.internal.handler.ThingHandlerInterface;

/**
 * {@link BrandCarNetSkoda} provides the Škoda specific functions of the API, portal URL us
 * <a href="https://www.skoda-connect.com">https://www.skoda-connect.com</a>
 *
 * @author Markus Michels - Initial contribution
 * @author Thomas Knaller - Maintainer
 * @author Dr. Yves Kreis - Maintainer
 */
@NonNullByDefault
public class BrandCarNetSkoda extends CarNetApi implements BrandAuthenticator {
    private static final ApiBrandProperties properties = new ApiBrandProperties();
    static {
        properties.brand = "VW"; // it's "VW", not "Škoda"
        properties.xcountry = "CZ";
        properties.clientId = "f9a2359a-b776-46d9-bd0c-db1904343117@apps_vw-dilab_com";
        properties.xClientId = "afb0473b-6d82-42b8-bfea-cead338c46ef";
        properties.authScope = "openid mbb profile";
        properties.apiDefaultUrl = CNAPI_DEFAULT_API_URL;
        properties.tokenUrl = CNAPI_VW_TOKEN_URL;
        properties.tokenRefreshUrl = properties.tokenUrl;
        properties.redirect_uri = "skodaconnect://oidc.login/";
        properties.xrequest = "cz.skodaauto.connect";
        properties.responseType = "token id_token";
        properties.xappVersion = "3.2.6";
        properties.xappName = "cz.skodaauto.connect";
    }

    public BrandCarNetSkoda(ThingHandlerInterface handler, ApiHttpClient httpClient, IdentityManager tokenManager,
            @Nullable ApiEventListener eventListener) {
        super(handler, httpClient, tokenManager, eventListener);
    }

    @Override
    public ApiBrandProperties getProperties() {
        return getSkodaProperties();
    }

    public static ApiBrandProperties getSkodaProperties() {
        return properties;
    }
}
