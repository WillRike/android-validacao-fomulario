package alura.com.br.alurafood.validator;

import android.widget.EditText;

import com.google.android.material.textfield.TextInputLayout;

import alura.com.br.alurafood.formatter.FormataTelefoneComDdd;

public class ValidaTelefoneComDDD {
    public static final String DEVE_TER_DEZ_OU_ONZE_DIGITOS = "Telefone deve ter entre 10 a 11 dígitos";
    private final TextInputLayout textInputTelefoneComDdd;
    private final EditText campoTelefoneComDdd;
    private final ValidacaoPadrao validacaoPadrao;
    private final FormataTelefoneComDdd formatador = new FormataTelefoneComDdd();

    public ValidaTelefoneComDDD(TextInputLayout textInputTelefoneComDdd) {
        this.textInputTelefoneComDdd = textInputTelefoneComDdd;
        this.campoTelefoneComDdd = textInputTelefoneComDdd.getEditText();
        this.validacaoPadrao = new ValidacaoPadrao(textInputTelefoneComDdd);
    }

    private boolean validaEntreDezouOnzeDigitos(String telefoneComDdd) {
        int digitos = telefoneComDdd.length();
        if (digitos < 10 || digitos > 11) {
            textInputTelefoneComDdd.setError(DEVE_TER_DEZ_OU_ONZE_DIGITOS);
            return false;
        }
        return true;
    }

    public boolean estaValido() {
        if (!validacaoPadrao.estaValido()) return false;
        String telefoneComDdd = campoTelefoneComDdd.getText().toString();
        if (!validaEntreDezouOnzeDigitos(telefoneComDdd)) return false;
        adicionaFormatacao(telefoneComDdd);
        return true;
    }

    private void adicionaFormatacao(String telefoneComDdd) {
//        StringBuilder sb = new StringBuilder();
//        int digitos = telefoneComDdd.length();
//        for (int i = 0; i < digitos; i++) {
//            if (i == 0) {
//                sb.append("(");
//            }
//            char digito = telefoneComDdd.charAt(i);
//            sb.append(digito);
//            if (i == 1) {
//                sb.append(")");
//            }
//            if (digitos == 10 && i == 5 || digitos == 11 && i == 6) {
//                sb.append("-");
//            }
//        }
//        String telefoneFormatado = sb.toString();
        String telefoneFormatado = formatador.formata(telefoneComDdd);
        campoTelefoneComDdd.setText(telefoneFormatado);
    }
}
