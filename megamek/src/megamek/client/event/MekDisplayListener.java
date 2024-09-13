/*
 * MegaMek - Copyright (C) 2005 Ben Mazur (bmazur@sev.org)
 * Copyright (c) 2024 - The MegaMek Team. All Rights Reserved.
 *
 * This file is part of MegaMek.
 *
 * MegaMek is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * MegaMek is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with MegaMek. If not, see <http://www.gnu.org/licenses/>.
 */

package megamek.client.event;

/**
 * Classes which implement this interface provide methods that deal with the
 * events that are generated when the MekDisplay is changed.
 * <p>
 * After creating an instance of a class that implements this interface it can
 * be added to a Board using the <code>addMekDisplayListener</code> method
 * and removed using the <code>removeMekDisplayListener</code> method. When
 * MekDisplay is changed the appropriate method will be invoked.
 * </p>
 *
 * @see MekDisplayListenerAdapter
 * @see MekDisplayEvent
 */
public interface MekDisplayListener extends java.util.EventListener {

    /**
     * Sent when user selects a weapon in the weapon panel.
     *
     * @param b an event
     */
    public void weaponSelected(MekDisplayEvent b);
}