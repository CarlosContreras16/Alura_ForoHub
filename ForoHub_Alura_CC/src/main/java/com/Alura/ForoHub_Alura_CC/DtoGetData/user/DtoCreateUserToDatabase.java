package com.Alura.ForoHub_Alura_CC.DtoGetData.user;

import com.Alura.ForoHub_Alura_CC.models.Profile;

public record DtoCreateUserToDatabase(String username,
                                      String email,
                                      String passwordEncrypted,
                                      Profile typeOfProfile) {
}
