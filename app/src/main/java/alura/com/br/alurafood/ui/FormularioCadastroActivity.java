package alura.com.br.alurafood.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;

import alura.com.br.alurafood.R;
import br.com.caelum.stella.format.CPFFormatter;
import br.com.caelum.stella.validation.CPFValidator;
import br.com.caelum.stella.validation.InvalidStateException;

public class FormularioCadastroActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_cadastro);
        inicializaCampos();
    }

    private void inicializaCampos() {
        configuraCampoNome();
        configuraCampoCpf();
        configuraCampoTelefone();
        configuraCampoEmail();
        configuraCampoSenha();
    }

    private void configuraCampoSenha() {
        TextInputLayout textInputSenha = findViewById(R.id.formulario_cadastro_senha);
        final EditText campoSenha = textInputSenha.getEditText();
        adicionaValidacaoPadrao(textInputSenha);
    }

    private void configuraCampoEmail() {
        TextInputLayout textInputEmail = findViewById(R.id.formulario_cadastro_email);
        final EditText campoEmail = textInputEmail.getEditText();
        adicionaValidacaoPadrao(textInputEmail);
    }

    private void configuraCampoTelefone() {
        TextInputLayout textInputTelefone = findViewById(R.id.formulario_cadastro_telefone);
        final EditText campoTelefone = textInputTelefone.getEditText();
        adicionaValidacaoPadrao(textInputTelefone);
    }

    private void configuraCampoCpf() {
        TextInputLayout textInputCpf = findViewById(R.id.formulario_cadastro_cpf);
        EditText campoCpf = textInputCpf.getEditText();
        assert campoCpf != null;
        CPFFormatter cpfFormatter = new CPFFormatter();
        campoCpf.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                String cpf = campoCpf.getText().toString();
                if (!hasFocus) {
                    if (!validaCampoObrigatorio(cpf, textInputCpf)) return;
                    if (!validaCampoComOnzeDigitos(cpf)) return;
                    if (!validaCalculoCpf(cpf, textInputCpf)) return;

                    removeErro(textInputCpf);

                    String cpfFormatado = cpfFormatter.format(cpf);
                    campoCpf.setText(cpfFormatado);
                } else {
                    try {
                        String cpfSemFormato = cpfFormatter.unformat(cpf);
                        campoCpf.setText(cpfSemFormato);
                    } catch (IllegalArgumentException e) {
                        Log.e("erro formatação cpf", e.getMessage());
                    }
                }
            }

            private Boolean validaCampoComOnzeDigitos(String cpf) {
                if (cpf.length() != 11) {
                    textInputCpf.setError("O CPF precisa ter 11 dígitos");
                    return false;
                }
                return true;
            }

            private Boolean validaCampoObrigatorio(String cpf, TextInputLayout textInputCpf) {
                if (cpf.isEmpty()) {
                    textInputCpf.setError("Campo Obrigatório!");
                    return false;
                }
                return true;
            }
        });
    }

    private boolean validaCalculoCpf(String cpf, TextInputLayout textInputCpf) {
        try {
            CPFValidator cpfValidator = new CPFValidator();
            cpfValidator.assertValid(cpf);
        } catch (InvalidStateException e) {
            textInputCpf.setError("CPF Inválido");
            return false;
        }
        return true;
    }

    private void removeErro(TextInputLayout textInputCpf) {
        textInputCpf.setError(null);
        textInputCpf.setErrorEnabled(false);
    }

    private void configuraCampoNome() {
        TextInputLayout textInputNome = findViewById(R.id.formulario_cadastro_nome);
        final EditText campoNome = textInputNome.getEditText();
        adicionaValidacaoPadrao(textInputNome);
    }

    private void adicionaValidacaoPadrao(TextInputLayout textInputCampo) {
        EditText campo = textInputCampo.getEditText();
        assert campo != null;
        campo.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                String texto = campo.getText().toString();
                if (!hasFocus) {
                    validaCampoObrigatorio(texto, textInputCampo);
                }
            }
        });
    }

    private void validaCampoObrigatorio(String texto, TextInputLayout textInputCampo) {
        if (texto.isEmpty()) {
            textInputCampo.setError("Campo Obrigatório!");
        } else {
            removeErro(textInputCampo);
        }
    }
}