
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

function setActiveMenu(){
	var paginaAtual = $a("#activeMenu").val();
	
	if(paginaAtual != null && paginaAtual != ""){
		var menuClass = $a("#"+paginaAtual).attr('class');
		$a("#"+paginaAtual).attr('class', menuClass + " active");
	}
}