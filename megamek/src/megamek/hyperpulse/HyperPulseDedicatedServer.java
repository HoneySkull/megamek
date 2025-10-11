/*
 * Copyright (C) 2005-2025 The MegaMek Team. All Rights Reserved.
 *
 * This file is part of MegaMek.
 *
 * MegaMek is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License (GPL),
 * version 3 or (at your option) any later version,
 * as published by the Free Software Foundation.
 *
 * MegaMek is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty
 * of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU General Public License for more details.
 *
 * A copy of the GPL should have been included with this project;
 * if not, see <https://www.gnu.org/licenses/>.
 *
 * NOTICE: The MegaMek organization is a non-profit group of volunteers
 * creating free software for the BattleTech community.
 *
 * MechWarrior, BattleMech, `Mech and AeroTech are registered trademarks
 * of The Topps Company, Inc. All Rights Reserved.
 *
 * Catalyst Game Labs and the Catalyst Game Labs logo are trademarks of
 * InMediaRes Productions, LLC.
 *
 * MechWarrior Copyright Microsoft Corporation. MegaMek was created under
 * Microsoft's "Game Content Usage Rules"
 * <https://www.xbox.com/en-US/developers/rules> and it is not endorsed by or
 * affiliated with Microsoft.
 */
package megamek.hyperpulse;

import java.io.IOException;
import java.net.ServerSocket;

import megamek.common.commandLine.AbstractCommandLineParser;
import megamek.common.commandLine.ClientServerCommandLineParser;
import megamek.common.commandLine.MegaMekCommandLineFlag;
import megamek.logging.MMLogger;

/**
 * HyperPulse Dedicated Server is a wrapper class that serves as an anchor point to launch a HyperPulse dedicated server
 * from the command line similar to a normal MegaMek dedicated server.  It parses command line argument to determine the
 * port to listen on and spins up a background thread that listens for incoming client and host connections.  The
 * HyperPulse dedicated server is designed to allow a local host and clients to connect through this server so that the
 * host can configure a private game locally without a need to configure fire wall settings.
 * <p>
 * Example: 'MegaMek.exe -hyperpulse -port 3050'
 */
public class HyperPulseDedicatedServer {
    private static final MMLogger logger = MMLogger.create(HyperPulseDedicatedServer.class);

    /**
     * Starts the HyperPulse server with optional parameters such as the port number. Example: 'MegaMek.exe -hyperpulse
     * -port 3050'
     *
     * @param args - the remaining command line arguments that do not include '-hyperpulse'.
     */
    public static void start(String[] args) {
        ClientServerCommandLineParser parser = new ClientServerCommandLineParser(args,
              MegaMekCommandLineFlag.HYPERPULSE.toString(), true, false, false);
        try {
            parser.parse();
        } catch (AbstractCommandLineParser.ParseException e) {
            logger.error("Incorrect arguments:{}\n{}", e.getMessage(), parser.help());
            return;
        }

        ClientServerCommandLineParser.Resolver resolver = parser.getResolver(
              null, HyperPulseServer.DEFAULT_LISTEN_PORT, null, null);

        startHyperPulseServer(resolver.port);
    }

    /**
     * Set up a new <code>HyperPulseServer</code> and start it listening on the given port number.
     *
     * @param port number to listen for incoming socket connections.
     */
    private static void startHyperPulseServer(int port) {

        try {
            HyperPulseServer connectServer = new HyperPulseServer(new ServerSocket(port));
            connectServer.startProcessingConnections();
            logger.info("HyperPulse dedicated server server is running...");
        } catch (IOException e) {
            logger.error("Error: failed to start HyperPulse server.  See stack trace.");
            logger.error(e.toString());
        } catch (Exception ex) {
            logger.error("Error: could not start HyperPulse server at localhost:{}", port, ex);
        }

        return;
    }
}
