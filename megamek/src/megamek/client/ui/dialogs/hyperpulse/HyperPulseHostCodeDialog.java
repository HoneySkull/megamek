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
package megamek.client.ui.dialogs.hyperpulse;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import java.awt.Frame;

/**
 * A custom dialog for entering a host ID. This class extends JDialog and provides a simple UI with a text field for
 * users to input a host ID and buttons to accept or cancel the input.  The dialog blocks user interaction with the
 * parent frame until it is closed.
 * <p>
 * When connecting as a client to a dedicated HyperPulse server, the host ID is required. This dialog is used to
 * allow the player to enter the host ID provided by the player hosting their private game on the HyperPulse server.
 */
public class HyperPulseHostCodeDialog extends JDialog {
    private final JTextField hostCodeField;
    private String result = null;

    public HyperPulseHostCodeDialog(Frame owner) {
        super(owner, "Enter HyperPulse Host Code", true);
        setLayout(new BorderLayout());

        hostCodeField = new JTextField(20);
        JPanel inputPanel = new JPanel();
        inputPanel.add(new JLabel("Host Code:"));
        inputPanel.add(hostCodeField);
        add(inputPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        JButton acceptButton = new JButton("Accept");
        acceptButton.addActionListener(e -> {
            result = hostCodeField.getText();
            dispose();
        });
        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(e -> dispose());
        buttonPanel.add(acceptButton);
        buttonPanel.add(cancelButton);
        add(buttonPanel, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(owner);
    }

    public String getResult() {
        return result;
    }
}
