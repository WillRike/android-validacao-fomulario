package alura.com.br.alurafood.validator;

import android.widget.EditText;

import androidx.annotation.NonNull;

import com.google.android.material.textfield.TextInputLayout;

import br.com.caelum.stella.format.CPFFormatter;
import br.com.caelum.stella.validation.CPFValidator;
import br.com.caelum.stella.validation.InvalidStateException;

public class ValidaCpf {

    public static final String CPF_INVALIDO = "CPF Inválido";
    public static final String DEVE_TER_ONZE_DIGITOS = "O CPF precisa ter 11 dígitos";
    private final TextInputLayout textInputCpf;
    private final EditText campoCpf;
    private final ValidacaoPadrao validadorPadrao;
    private final CPFFormatter cpfFormatter = new CPFFormatter();

    public ValidaCpf(TextInputLayout textInputCpf) {
        this.textInputCpf = textInputCpf;
        this.campoCpf = textInputCpf.getEditText();
        this.validadorPadrao = new ValidacaoPadrao(textInputCpf);
    }

    private boolean validaCalculoCpf(String cpf) {
        try {
            CPFValidator cpfValidator = new CPFValidator();
            cpfValidator.assertValid(cpf);
        } catch (InvalidStateException e) {
            textInputCpf.setError(CPF_INVALIDO);
            return false;
        }
        return true;
    }

    private Boolean validaCampoComOnzeDigitos(String cpf) {
        if (cpf.length() != 11) {
            textInputCpf.setError(DEVE_TER_ONZE_DIGITOS);
            return false;
        }
        return true;
    }

    public boolean estaValido() {
        if (!validadorPadrao.estaValido()) return false;
        String cpf = getCpf();
        if (!validaCampoComOnzeDigitos(cpf)) return false;
        if (!validaCalculoCpf(cpf)) return false;
        adicionaFormatacao(cpf);
        return true;
    }

    private void adicionaFormatacao(String cpf) {
        String cpfFormatado = cpfFormatter.format(cpf);
        campoCpf.setText(cpfFormatado);
    }

    @NonNull
    private String getCpf() {
        return campoCpf.getText().toString();
    }


}
