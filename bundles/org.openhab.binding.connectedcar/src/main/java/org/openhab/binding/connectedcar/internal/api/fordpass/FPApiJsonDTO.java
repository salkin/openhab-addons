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
package org.openhab.binding.connectedcar.internal.api.fordpass;

import static org.openhab.binding.connectedcar.internal.api.ApiDataTypesDTO.*;

import java.util.ArrayList;

import org.openhab.binding.connectedcar.internal.api.ApiDataTypesDTO.ApiActionRequest;
import org.openhab.binding.connectedcar.internal.api.fordpass.FPApiJsonDTO.FPVehicleStatusData.FPVehicleStatus.FPStatusBattery.FPStatusBatHealth;

import com.google.gson.annotations.SerializedName;

/**
 * {@link FPApiJsonDTO} defines data formats for Ford Connect API
 *
 * @author Markus Michels - Initial contribution
 * @author Thomas Knaller - Maintainer
 * @author Dr. Yves Kreis - Maintainer
 */
public class FPApiJsonDTO {
    public final static String FPSERVICE_ENGINE = "engine";
    public final static String FPSERVICE_DOORS = "doors";
    public final static String FPSERVICE_CLIMATISATION = "climatisation";
    public final static String FPSERVICE_CHARGER = "charger";

    public static class FPErrorResponse {
        public static class FPErrorMessage {
            public static class FPErrorDetails {
                public String statusContext;
                public String statusCode;
                public String message;
            }

            public String httpStatus;
            public String status;
            public String requestStatus;
            public FPErrorDetails error;
        }

        @SerializedName("Response")
        public FPErrorMessage response;
    }

    public static class FPVehicleListData {
        public static class FPVehicleData {
            public static class FPVehicle {
                public String vin, nickName, vehicleType, color;
                public String modelName, modelCode, modelYear, make;
                public Integer tcuEnabled;
                public Integer ngSdnManaged;
                public Integer vehicleAuthorizationIndicator;
            }

            @SerializedName("$values")
            public ArrayList<FPVehicle> values;
        }

        public FPVehicleData vehicles;
        public String status;
        public String version;
    }

    public static class FPVehicleStatusData {
        public static class FPVehicleStatus {
            public static class FPStatusStringValue {
                public String value;
                public String status;
                public String timestamp;
            }

            public static class FPStatusIntValue {
                public Integer value;
                public String status;
                public String timestamp;
            }

            public static class FPStatusDoubleValue {
                public Double value;
                public String status;
                public String timestamp;
            }

            public static class FPStatusBooleanValue {
                public Boolean value;
                public String status;
                public String timestamp;
            }

            public static class FPStatusGPS {
                /*
                 * "latitude":"26.9543817",
                 * "longitude":"-82.3306200",
                 * "gpsState":"UNSHIFTED",
                 * "status":"CURRENT",
                 * "timestamp":"07-29-2021 01:03:40"
                 */
                public Double latitude;
                public Double longitude;
                public String gpsState;
                public String status;
                public String timestamp;
            }

            public static class FPStatusRemoteStart {
                /*
                 * "remoteStartDuration":0,
                 * "remoteStartTime":0,
                 * "status":"CURRENT",
                 * "timestamp":"07-29-2021 01:03:40"
                 */
                public Integer remoteStartDuration;
                public Integer remoteStartTime;
                public String status;
                public String timestamp;
            }

            public static class FPStatusBattery {
                /*
                 * "batteryHealth":{
                 * "value":"STATUS_GOOD",
                 * "timestamp":"07-29-2021 01:03:21"
                 * },
                 * "batteryStatusActual":{
                 * "value":13,
                 * "status":"CURRENT",
                 * "timestamp":"07-29-2021 01:03:40"
                 * }
                 */

                public static class FPStatusBatHealth {
                    public String value;
                    public String timestamp;
                }

                public static class FPStatusBatActual {
                    public Integer value;
                    public String status;
                    public String timestamp;
                }

                public FPStatusBatHealth batteryHealth;
                public FPStatusBatActual batteryStatusActual;
            }

            public static class FPStatusFuel {
                public Double fuelLevel;
                public Double distanceToEmpty;
                public String status;
                public String timestamp;
            }

            public static class FPStatusOil {
                /*
                 * "oilLife":"STATUS_GOOD",
                 * "oilLifeActual":98,
                 * "status":"CURRENT",
                 * "timestamp":"07-29-2021 01:03:40"
                 */
                public String oilLife;
                public Integer oilLifeActual;
                public String status;
                public String timestamp;
            }

            public static class FPStatusTirePres {
                /*
                 * "value":"STATUS_GOOD",
                 * "timestamp":"07-29-2021 01:03:21"
                 */
                public String value;
                public String timestamp;
            }

            public static class FPTpms {
                FPStatusDoubleValue tirePressureByLocation;
                FPStatusStringValue tirePressureSystemStatus;
                FPStatusDoubleValue dualRearWheel;
                FPStatusStringValue leftFrontTireStatus;
                FPStatusStringValue leftFrontTirePressure;
                FPStatusStringValue rightFrontTireStatus;
                FPStatusDoubleValue rightFrontTirePressure;
                FPStatusStringValue outerLeftRearTireStatus;
                FPStatusStringValue outerLeftRearTirePressure;
                FPStatusStringValue outerRightRearTireStatus;
                FPStatusStringValue outerRightRearTirePressure;
                FPStatusStringValue innerLeftRearTireStatus;
                FPStatusStringValue innerLeftRearTirePressure;
                FPStatusStringValue innerRightRearTireStatus;
                FPStatusStringValue innerRightRearTirePressure;
                FPStatusStringValue recommendedRearTirePressure;
            }

            public static class FPCssSettings {
                public String timestamp;
                public Integer location;
                public Integer vehicleConnectivity;
                public Integer vehicleData;
                public Integer drivingCharacteristics;
                public Integer contacts;
            }

            public static class FPWindowPosition {
                FPStatusStringValue driverWindowPosition;
                FPStatusStringValue passWindowPosition;
                FPStatusStringValue rearDriverWindowPos;
                FPStatusStringValue rearPassWindowPos;
            }

            public static class FPDoorStatus {
                FPStatusStringValue rightRearDoor;
                FPStatusStringValue leftRearDoor;
                FPStatusStringValue driverDoor;
                FPStatusStringValue passengerDoor;
                FPStatusStringValue hoodDoor;
                FPStatusStringValue tailgateDoor;
                FPStatusStringValue innerTailgateDoor;
            }

            public static class FPDieselSystemStatus {
                /*
                 * Formats for those are missing, to be added
                 * "exhaustFluidLevel":null,
                 * "filterSoot":null,
                 * "ureaRange":null,
                 * "metricType":null,
                 * "filterRegenerationStatus":null
                 */
            }

            public String vin;
            public FPStatusStringValue lockStatus;
            public FPStatusStringValue alarm;
            @SerializedName("PrmtAlarmEvent")
            public FPStatusStringValue prmtAlarmEvent;
            public FPStatusDoubleValue odometer;
            public FPStatusFuel fuel;
            public FPStatusGPS gps;
            public FPStatusRemoteStart remoteStart;
            public FPStatusDoubleValue remoteStartStatus;
            public FPStatusBatHealth battery;
            public FPStatusOil oil;
            public FPStatusTirePres tirePressure;
            public String authorization;
            @SerializedName("TPMS")
            public FPTpms tpms;
            public FPStatusBooleanValue firmwareUpgInProgress;
            public FPStatusBooleanValue deepSleepInProgress;
            public FPCssSettings cssSettings;
            public String lastRefresh;
            public String lastModifiedDate;
            public String serverTime;
            public FPStatusStringValue outandAbout;
            public FPWindowPosition windowPosition;
            public FPDoorStatus doorStatus;
            public FPStatusStringValue ignitionStatus;
            public FPDieselSystemStatus dieselSystemStatus;
            public FPStatusDoubleValue batteryFillLevel;
            public FPStatusDoubleValue elVehDTE;
            public FPStatusStringValue hybridModeStatus;
            public FPStatusStringValue chargingStatus;
            public FPStatusIntValue plugStatus;
            public FPStatusStringValue chargeStartTime;
            public FPStatusStringValue chargeEndTime;
            public FPStatusStringValue preCondStatusDsply;
            public FPStatusStringValue chargerPowertype;
            public FPStatusStringValue batteryPerfStatus;
            /*
             * Formats for those are missing, to be added
             *
             * "batteryChargeStatus":null,
             * "dcFastChargeData":null,
             * "batteryTracLowChargeThreshold":null,
             * "battTracLoSocDDsply":null,
             */
        }

        public FPVehicleStatus vehiclestatus;
        public String version;
        public String status;
    }

    public static class FPActionRequest extends ApiActionRequest {
        public FPActionRequest(String service, String action, FPActionResponse rsp) {
            // normalize the response type
            this.service = service;
            this.action = action;
            this.requestId = rsp.commandId;
        }

        public static class FPActionResponse {
            /*
             * {
             * "$id":"1",
             * "commandId":"db8a4d86-5a21-4766-b114-0dc4cd52b09e",
             * "status":200,
             * "version":"1.0.0"
             * }
             */
            public String commandId;
            public String status;
            public String version;

            public String mapStatusCode() {
                // seems to be a http status code, but not sure if other values could be reported
                switch (status) {
                    case "200":
                        return API_REQUEST_SUCCESSFUL;
                    case "401":
                        return API_REQUEST_SECURITY;
                    case "552":
                        return API_REQUEST_IN_PROGRESS;
                    case "402": // payment required?
                    default:
                        return API_REQUEST_ERROR;
                }
            }

            public boolean isError() {
                return status.equalsIgnoreCase(API_REQUEST_ERROR);
            }
        }
    }
}
