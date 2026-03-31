package com.darin.validateuracces.regle;

import com.darin.validateuracces.dto.DemandeAcces;
import com.darin.validateuracces.dto.ResultatRegle;

public interface RegleValidationAcces {
    ResultatRegle evaluer(DemandeAcces demandeAcces);
    String obtenirNomRegle();
}
