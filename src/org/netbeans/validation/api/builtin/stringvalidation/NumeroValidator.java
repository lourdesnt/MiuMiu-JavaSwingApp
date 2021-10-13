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
public class NumeroValidator extends StringValidator {

    @Override
    public void validate(Problems problems, String compName, String model) {
        if(model.isEmpty()){
            String message = NbBundle.getMessage(NombreValidator.class, "MSG_MAY_NOT_BE_EMPTY" , compName);
            problems.add(message);
        }
        for(int i=0; i<model.length(); i++){
            if(!Character.isDigit(model.charAt(i))){
                String message = NbBundle.getMessage(NombreValidator.class, "ERR_NOT_INTEGER" , compName);
                problems.add(message);
            }
        }
//        if(model.charAt(0)=='-'){
//            String message = NbBundle.getMessage(NombreValidator.class, "ERR_NEGATIVE_NUMBER" , compName);
//            problems.add(message);
//        }
    }
    
}
