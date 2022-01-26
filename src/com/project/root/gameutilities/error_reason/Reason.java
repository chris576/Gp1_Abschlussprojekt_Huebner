package com.project.root.gameutilities.error_reason;

import com.project.root.gameutilities.InvalidThrowException;

/**
 * The possible reasons of an {@link InvalidThrowException}. Currently the only reason is {@link #FALLENOF}.
 * The purpose of this Class is to provide the ability to add different reasons of an invalid throw and react to them.
 * Therefore the code follows the open closed principle.
 *
 * @author Christopher Hübner
 * @version 1.0 15.01.2021
 */
public enum Reason {
    /**
     * Invalid throw because at least one dice is fallen down.
     */
    FALLENOF;
}