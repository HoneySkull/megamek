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
package megamek.client.ui.dialogs.gameConnectionDialogs;

import java.awt.GridBagConstraints;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import megamek.client.ui.Messages;

public class HostHyperPulseDialog extends ConnectDialog {
    //private JTextField serverAddressField;

    public HostHyperPulseDialog(JFrame frame) {
        super(frame, "");
        this.setTitle(Messages.getString("HyperPulse.title"));
    }

    @Override
    protected void addOptionRows(JPanel middlePanel, GridBagConstraints c) {
        JLabel serverAddressLabel = new JLabel(Messages.getString("HyperPulse.server.label"), SwingConstants.RIGHT);
        JLabel portL = new JLabel(Messages.getString("HyperPulse.port.label"), SwingConstants.RIGHT);

        addOptionRow(middlePanel, c, serverAddressLabel, getServerAddressField());
        addOptionRow(middlePanel, c, portL, getPortField());
    }

    @Override
    protected int getLastConnectPort() {
        // TODO: replace this call with a hyperpulse specific client preferences call.
        return getClientPreferences().getLastConnectPort();
    }

    @Override
    protected String getLastConnectAddr() {
        // TODO: replace this call with a hyperpulse specific client preferences call.
        return getClientPreferences().getLastConnectAddr();
    }

    @Override
    protected void setLastConnectAddr(String serverAddress) {
        // Don't save the server address for HyperPulse.
    }
}
