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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Logger;

import megamek.common.net.connections.AbstractConnection;
import org.junit.jupiter.api.Test;

public class HyperPulseServerTest {

    @Test
    void testGetConnectionFromIdWithValidId() {
        ServerSocket serverSocket = mock(ServerSocket.class);
        HyperPulseServer server = new HyperPulseServer(serverSocket);

        AbstractConnection mockConnection = mock(AbstractConnection.class);
        when(mockConnection.getId()).thenReturn(42);

        // Simulate adding the connection to the list in the server
        server.addConnection(mockConnection);

        // Verify that the correct connection is returned for a valid ID
        assertEquals(mockConnection, server.getConnectionFromId(42));
    }

    @Test
    void testGetConnectionFromIdWithInvalidId() {
        ServerSocket serverSocket = mock(ServerSocket.class);
        HyperPulseServer server = new HyperPulseServer(serverSocket);

        AbstractConnection mockConnection = mock(AbstractConnection.class);
        when(mockConnection.getId()).thenReturn(1);

        // Simulate adding the connection to the list in the server
        server.addConnection(mockConnection);

        // Verify that the method returns null for an invalid ID
        assertNull(server.getConnectionFromId(99));
    }

    @Test
    void testProcessNewClientConnectionWithValidSocket() {
        ServerSocket serverSocket = mock(ServerSocket.class);
        Socket mockSocket = mock(Socket.class);

        HyperPulseServer server = new HyperPulseServer(serverSocket);
        server.processNewClientConnection(mockSocket);

        // Verify that a new connection handler has been added to the server's connectionHandlers.
        assertEquals(1, server.connectionHandlers.size());
    }

    @Test
    void testProcessNewClientConnectionWithNullSocket() {
        ServerSocket serverSocket = mock(ServerSocket.class);
        HyperPulseServer server = new HyperPulseServer(serverSocket);

        server.processNewClientConnection(null);

        // Verify no interactions with connectionHandlers since no socket was provided
        assertTrue(server.connectionHandlers.isEmpty());
    }

    @Test
    void testLogLocalHostDetails() {
        Logger logger = mock(Logger.class);
        ServerSocket serverSocket = mock(ServerSocket.class);

        // Pretend we're configured to listen to port 80
        when(serverSocket.getLocalPort()).thenReturn(80);

        HyperPulseServer server = new HyperPulseServer(serverSocket);
        server.logLocalHostDetails(logger::info);

        //print the local host details to the standard out for info only.
        server.logLocalHostDetails(System.out::println);

        // If successful, the ConnectServer will establish a socket connection on the given
        // port.  Verify the host name and at least one Internet address is posted to the log.
        verify(logger, atLeast(2)).info(anyString());
    }

    @Test
    void testReturnConfiguredListeningPortWithConfiguredServerSocket() {
        ServerSocket serverSocket = mock(ServerSocket.class);

        //Pretend we're configured to listen to port 99
        when(serverSocket.getLocalPort()).thenReturn(99);

        HyperPulseServer server = new HyperPulseServer(serverSocket);

        assertEquals(99, server.getLocalPort());
    }

    @Test
    void testReturnConfiguredListeningPortWithNullServerSocket() {
        HyperPulseServer server = new HyperPulseServer(null);

        assertEquals(HyperPulseServer.PORT_UNDEFINED, server.getLocalPort());
    }
}
