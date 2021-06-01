package sample.cipherViewModel;

import cipher.Cipher;
import sample.Controller;

import java.util.function.Supplier;

public interface CipherViewModel extends Controller, Supplier<Cipher> {

}