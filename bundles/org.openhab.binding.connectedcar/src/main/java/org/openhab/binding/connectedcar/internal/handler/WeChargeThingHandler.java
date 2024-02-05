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
package org.openhab.binding.connectedcar.internal.handler;

import static org.openhab.binding.connectedcar.internal.BindingConstants.CHANNEL_CONTROL_RESTART;

import java.time.ZoneId;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.openhab.binding.connectedcar.internal.api.ApiException;
import org.openhab.binding.connectedcar.internal.api.wecharge.WeChargeServiceStatus;
import org.openhab.binding.connectedcar.internal.provider.CarChannelTypeProvider;
import org.openhab.binding.connectedcar.internal.provider.ChannelDefinitions;
import org.openhab.binding.connectedcar.internal.util.TextResources;
import org.openhab.core.library.types.OnOffType;
import org.openhab.core.thing.ChannelUID;
import org.openhab.core.thing.Thing;
import org.openhab.core.types.Command;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The {@link WeChargeThingHandler} implements the Vehicle Handler for WeCharge
 *
 * @author Markus Michels - Initial contribution
 * @author Thomas Knaller - Maintainer
 * @author Dr. Yves Kreis - Maintainer
 */
@NonNullByDefault
public class WeChargeThingHandler extends ThingBaseHandler {
    private final Logger logger = LoggerFactory.getLogger(WeChargeThingHandler.class);

    public WeChargeThingHandler(Thing thing, TextResources resources, ZoneId zoneId, ChannelDefinitions idMapper,
            CarChannelTypeProvider channelTypeProvider) throws ApiException {
        super(thing, resources, zoneId, idMapper, channelTypeProvider);
    }

    /**
     * Register all available services
     */
    @Override
    public void registerServices() {
        services.clear();
        addService(new WeChargeServiceStatus(this, api));
    }

    @Override
    public boolean handleBrandCommand(ChannelUID channelUID, Command command) throws ApiException {
        String channelId = channelUID.getIdWithoutGroup();
        boolean processed = true;
        String action = "";
        String actionStatus = "";
        boolean switchOn = command == OnOffType.ON;
        logger.debug("{}: Channel {} received command {}", thingId, channelId, command);
        try {
            if (channelId.equals(CHANNEL_CONTROL_RESTART)) {
                action = "restart";
                actionStatus = api.controlEngine(switchOn);
                updateState(channelUID.getId(), OnOffType.OFF);
            } else {
                processed = false;
            }
        } catch (RuntimeException /* ApiException */ e) {
            if (command instanceof OnOffType) {
                updateState(channelUID.getId(), OnOffType.OFF);
            }
            throw e;
        }

        if (processed) {
            logger.debug("{}: Action {} submitted, initial status={}", thingId, action, actionStatus);
        }
        return processed;
    }
}
