package alura.com.br.alurafood.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;

import alura.com.br.alurafood.R;

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
        final EditText campoCpf = textInputCpf.getEditText();
        adicionaValidacaoPadrao(textInputCpf);
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
            textInputCampo.setError(null);
            textInputCampo.setErrorEnabled(false);
        }
    }
}