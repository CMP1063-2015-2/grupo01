
$a = jQuery.noConflict();
$a(document).ready(function() {
    var heightWindow = $a(window).height();
    var heightBody   = document.body.clientHeight;
    if(heightWindow > heightBody){
        $a("#footer").css('position', 'fixed');
        $a("#footer").css('bottom', '0');
    }
    
    $a('#dataTables-example2').DataTable({
        responsive: true,
        "language": {
        	 url: '/SmartClinic/resources/smartclinic/dataTable_ptBR.json'
        }
    });
    
    setActiveMenu();
    
});

function setMascara(){
    $a(".rg").mask("9999999999");
	$a("#form\\:cep").mask("99999-999");
    $a("#form\\:numero").mask("99999");
    $(".cpf").mask("999.999.999-99");
}

function setActiveMenu(){
	var paginaAtual = $a("#activeMenu").val();
	
	if(paginaAtual != null && paginaAtual != ""){
		var menuClass = $a("#"+paginaAtual).attr('class');
		$a("#"+paginaAtual).attr('class', menuClass + " active");
	}
}