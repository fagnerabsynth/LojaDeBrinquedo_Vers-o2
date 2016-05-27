var definida = "";
var itens = new Object();

function CriaMenu() {
    try {
        itens["Home"] = "Home";
        itens["Catálogo de Brinquedos"] = "Catálogo de Brinquedos";
        itens["Administração"] = "Administração";
        itens["Sobre"] = "Sobre";

        var cor = ['primary', 'danger', 'warning', 'info', 'success'];
        definida = cor[Math.floor(Math.random() * cor.length)];

        var html = '<div  role="group" aria-label="menu">';
        html += '<a  class="form-control btn btn-default active">Menu Principal</a>';

        for (var m in itens) {
            html += '<a  onclick="pagina(\'' + m + '\')" id="' + m[0] + m[1] + m[2] + '" class="btxx form-control btn btn-' + definida + '" >' + itens[m] + '</a>';
        }
        html += '</div>';
        jQuery('#menu').addClass('bg-' + definida);
        jQuery('#menu').css({'padding-top': '10px', 'padding-bottom': '10px'});

        jQuery('#menu').html(html);
    } catch (e) {
        alert(e);
    }
}

function paginaComParametros(pagina, dados) {
    var parametros = new Object();
    parametros["pesquisa"] = dados;
    parametros["cor"] = definida;
    $.ajax({
        url: window.location.href.replace("#", "") + pagina,
        dataType: 'html',
        data: parametros,
        method: 'GET'}).done(function (e) {
        $('.btxx').removeClass('active');
        $('#' + pagina[0] + pagina[1] + pagina[2]).addClass('active');
        $('#conteudo').html(e);
    });

}


function pagina(pagina) {
    $.ajax({
        url: window.location.href.replace("#", "") + pagina,
        dataType: 'html',
        data: {cor: definida},
        method: 'GET'}).done(function (e) {
        $('.btxx').removeClass('active');
        $('#' + pagina[0] + pagina[1] + pagina[2]).addClass('active');
        $('#conteudo').html(e);
    });
}

function alteraPagina(pagina) {
    $.ajax({
        url: window.location.href.replace("#", "") + pagina,
        dataType: 'html',
        data: {cor: definida},
        method: 'GET'}).done(function (e) {
        $('#conteudo').html(e);
    });
}


function brinquedoExcluir(pagina, id, titulo) {
    if (confirm('Você realmente deseja excluir o brinquedo:\n' + titulo)) {
        $.ajax({
            url: window.location.href.replace("#", "") + pagina,
            dataType: 'script',
            data: {'apaga': id},
            method: 'post'}).done(function () {
            if (resultado) {
                alteraPagina("Administração");
            } else {
                alert('Não foi possivel apagar os dados\n\nPor favor tente novamente!');
            }
        });
    }
}

function brinquedoEditar(pagina, id) {
    $.ajax({
        url: window.location.href.replace("#", "") + pagina,
        dataType: 'html',
        data: {cor: definida, brinquedo: id},
        method: 'GET'}).done(function (e) {
        $('#conteudo').html(e);
    });
}


function manipulaBrinquedo(id) {
    var objeto = new Object();
    var erro = "";

    if ($('#descricao').val() !== "")
        objeto['descricao'] = $('#descricao').val();
    else
        erro = "erro";

    if ($('#categoria').val() !== '')
        objeto['categoria'] = $('#categoria').val();
    else
        erro = "erro";

    if ($('#marca').val() !== '')
        objeto['marca'] = $('#marca').val();
    else
        erro = "erro";

    if ($('#preco').val() !== '')
        objeto['preco'] = $('#preco').val();
    else
        erro = "erro";

    if ($('#imagem').val() !== '')
        objeto['imagem'] = $('#imagem').val();
    else
        erro = "erro";

    if ($('#datalhe').val() !== '')
        objeto['detalhe'] = $('#detalhe').val();
    else
        erro = "erro";

    if (id) {
        objeto['codigo'] = id;
    }

    if (erro === 'erro')
        alert('Por favor preencha todos os campos');
    else {

        $.ajax({
            url: window.location.href.replace("#", "") + "Manipula+Brinquedo",
            dataType: 'script',
            data: objeto,
            method: 'post'}).done(function () {
            if (resultado) {
                pagina("Administração");
            } else {
                alert('Não foi possivel inserir os dados\n\nPor favor tente novamente!');
            }
        });
    }
}

function formataDecimal(input) {
    var val = '' + (input.value).replace(/[^0-9]/g, '');
    var retorno = "";
    if (val.length > 2) {
        for (var i = 0; i < val.length; i++) {
            retorno += val[i];
            if (val.length - 2 === i + 1) {
                retorno += '.';
            }
        }
    } else if (val.length === 2 || val.length === 1) {
        retorno = val + ".00"
    } else {
        retorno = '';
    }
    input.value = retorno;
}

function SomenteNumero(e) {
    var tecla = (window.event) ? event.keyCode : e.which;
    if ((tecla > 47 && tecla < 58) || tecla === 46)
        return true;
    else {
        if (tecla === 8 || tecla === 0)
            return true;
        else
            return false;
    }
}



function abreCategoria(cat) {
    $.ajax({
        url: window.location.href.replace("#", "") + "Categoria",
        dataType: 'html',
        data: {cor: definida, categoria: cat},
        method: 'GET'}).done(function (e) {
        $('#conteudo').html(e);
    });
}


function abreBrinquedo(codigo) {
     $.ajax({
        url: window.location.href.replace("#", "") + "Brinquedo",
        dataType: 'html',
        data: {cor: definida, brinquedo: codigo},
        method: 'GET'}).done(function (e) {
        $('#conteudo').html(e);
    });   
}

function abreBrinquedoHome(codigo) {
     $.ajax({
        url: window.location.href.replace("#", "") + "Brinquedo",
        dataType: 'html',
        data: {cor: definida, brinquedo: codigo, local : "home"},
        method: 'GET'}).done(function (e) {
        $('#conteudo').html(e);
    });   
}
