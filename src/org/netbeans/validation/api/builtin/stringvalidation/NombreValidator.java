/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.netbeans.validation.api.builtin.stringvalidation;

import org.netbeans.validation.api.Problems;
import org.openide.util.NbBundle;

/**
 *
 * @author Lourdes
 */
public class NombreValidator extends StringValidator {

    @Override
    public void validate(Problems problems, String compName, String model) {
        if(model.isEmpty()){
            String message = NbBundle.getMessage(NombreValidator.class, "MSG_MAY_NOT_BE_EMPTY" , compName);
            problems.add(message);
        }
    }
    
}
