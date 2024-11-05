
$(document).ready(function () {
    $("#cpf_edit").mask("999.999.999-99");
});

$(document).ready(function () {
    $("#telefone").mask("(99) 9.9999-9999");
});

function validarCPF(cpf) {
    cpf = cpf.replace(/\D/g, ''); // Remove todos os caracteres não numéricos
    if (!isCPF(cpf)) {
        document.getElementById("cpf-invalido").style.display = "block";
        document.getElementById("cpf-valido").style.display = "none";
        document.getElementById("cadastrar").style.display = "none";
    } else {
        document.getElementById("cpf-invalido").style.display = "none";
        document.getElementById("cpf-valido").style.display = "block";
        document.getElementById("cadastrar").style.display = "block";
    }
}

function isCPF(CPF) {
    // considera-se erro CPF's formados por uma sequencia de numeros iguais
    if (CPF === "00000000000" ||
        CPF === "11111111111" ||
        CPF === "22222222222" || CPF === "33333333333" ||
        CPF === "44444444444" || CPF === "55555555555" ||
        CPF === "66666666666" || CPF === "77777777777" ||
        CPF === "88888888888" || CPF === "99999999999" ||
        (CPF.length !== 11))
        return false;

    let dig10, dig11;
    let soma, i, r, num, peso;

    // Calculo do 1o. Digito Verificador
    soma = 0;
    peso = 10;
    for (i = 0; i < 9; i++) {
        num = parseInt(CPF.charAt(i));
        soma = soma + (num * peso);
        peso = peso - 1;
    }
    r = 11 - (soma % 11);
    if ((r === 10) || (r === 11))
        dig10 = '0';
    else dig10 = String.fromCharCode(r + 48); // converte no respectivo caractere numerico

    // Calculo do 2o. Digito Verificador
    soma = 0;
    peso = 11;
    for (i = 0; i < 10; i++) {
        num = parseInt(CPF.charAt(i));
        soma = soma + (num * peso);
        peso = peso - 1;
    }
    r = 11 - (soma % 11);
    if ((r === 10) || (r === 11))
        dig11 = '0';
    else dig11 = String.fromCharCode(r + 48);

    // Verifica se os digitos calculados conferem com os digitos informados.
    if ((dig10 === CPF.charAt(9)) && (dig11 === CPF.charAt(10))) {
        return true;
    } else {
        return false;
    }
}


function imprimeCPF(CPF) {
    return (CPF.substring(0, 3) + "." + CPF.substring(3, 6) + "." +
        CPF.substring(6, 9) + "-" + CPF.substring(9, 11));
}



function limpa_formulário_cep() {
    //Limpa valores do formulário de cep.
    document.getElementById('rua').value = ("");
    document.getElementById('bairro').value = ("");
    document.getElementById('cidade').value = ("");
    document.getElementById('uf').value = ("");
}

function meu_callback(conteudo) {
    if (!("erro" in conteudo)) {
        //Atualiza os campos com os valores.
        document.getElementById('rua').value = (conteudo.logradouro);
        document.getElementById('bairro').value = (conteudo.bairro);
        document.getElementById('cidade').value = (conteudo.localidade);
        document.getElementById('uf').value = (conteudo.uf);
    } //end if.
    else {
        //CEP não Encontrado.
        limpa_formulário_cep();
        alert("CEP não encontrado.");
    }
}

function pesquisacep(valor) {

    //Nova variável "cep" somente com dígitos.
    var cep = valor.replace(/\D/g, '');

    //Verifica se campo cep possui valor informado.
    if (cep != "") {

        //Expressão regular para validar o CEP.
        var validacep = /^[0-9]{8}$/;

        //Valida o formato do CEP.
        if (validacep.test(cep)) {

            //Preenche os campos com "..." enquanto consulta webservice.
            document.getElementById('rua').value = "...";
            document.getElementById('bairro').value = "...";
            document.getElementById('cidade').value = "...";
            document.getElementById('uf').value = "...";

            //Cria um elemento javascript.
            var script = document.createElement('script');

            //Sincroniza com o callback.
            script.src = 'https://viacep.com.br/ws/' + cep + '/json/?callback=meu_callback';

            //Insere script no documento e carrega o conteúdo.
            document.body.appendChild(script);

        } //end if.
        else {
            //cep é inválido.
            limpa_formulário_cep();
            alert("Formato de CEP inválido.");
        }
    } //end if.
    else {
        //cep sem valor, limpa formulário.
        limpa_formulário_cep();
    }
};