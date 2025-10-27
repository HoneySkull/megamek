/*
 * Copyright (C) 2025-2025 The MegaMek Team. All Rights Reserved.
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

import megamek.client.AbstractClient;
import megamek.common.game.Game;
import megamek.common.game.IGame;
import megamek.common.net.packets.Packet;

/**
 * This is a special type of client meant to be used with the HyperPulse dedicated server.  this Host Client runs on the
 * local host and makes a primary outgoing connection to the HyperPulse dedicated server.  It then receives incoming
 * client packets that have been forwarded to the dedicated server and forwards them to the local server.
 * <p>
 * This host client also maintains a collection of specialized clients that are proxy connections for the remote
 * connections logged into the dedicated hyperpuulse server.  When the local server sends packets to the client proxies,
 * they are then forwarded to the HyperPulse dedicated server where they are in turn sent to the original remote
 * clients.
 */
public class HyperPulseHostClient extends AbstractClient {

    private final IGame localGame = new Game();

    /**
     * Construct a HyperPulseHostClient to connect to a specified dedicated HyperPuslse server to forward package data
     * from remote clients also connected to the same HyperPulse server and associated with this host.
     *
     * @param host the hostname
     * @param port the host port
     */
    public HyperPulseHostClient(String host, int port) {
        super("HyperPulseHostAdaptor", host, port);
    }

    /**
     * Handles any Packets that are incoming from the HyperPulse dedicated server.  This is different from a normal
     * client as this type of client will maintain a set of internal connections to the local server and listen for
     * client packets from the dedicated HyperPulse server, determine which client it is associated with and then
     * forward to the local server.
     *
     * @param packet The packet from the HyperPulse dedicated server to forward to the local server.
     *
     * @return True when the packet has been handled
     *
     * @throws Exception If some error occurred.
     */
    @Override
    protected boolean handleGameSpecificPacket(Packet packet) throws Exception {
        return false;
    }

    /**
     * Handles the handshake process for the HyperPulse dedicated server communication protocol.
     *
     * @param packet The packet that contains relevant data for the handshake process.
     */
    @Override
    protected void handleHyperPulseHandshake(Packet packet) {

    }

    /**
     * Returns the game of this client as a game-type independent IGame. Note that the game object is only updated, not
     * replaced and a reference to it can therefore be kept.
     *
     * @return The game of this client
     */
    @Override
    public IGame getGame() {
        // This is unused by the HyperPulseHostClient adaptor.
        return localGame;
    }

    /**
     * @return True when the currently active turn is a turn for the local player
     */
    @Override
    public boolean isMyTurn() {
        return false;
    }
}
