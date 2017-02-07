
$('.AddAnn').hover(function() {
	$(".containIniAdd").hide();
	$(".containAnn").show();
},function() {
	$(".containIniAdd").show();
	$(".containAnn").hide();
});

$('.consAnn').hover(function() {
	$(".containCons").hide();
	$(".containVis").show();
},function() {
	$(".containCons").show();
	$(".containVis").hide();
});

$('.modifP').hover(function() {
	$(".modP").hide();
	$(".modiP").show();
},function() {
	$(".modP").show();
	$(".modiP").hide();
});

$(function() 
{
  $(".containVis").hide();
  $(".containVis").append( "<h3>Découvrir toutes les annonces en France</h3>" );
  $(".containAnn").hide();
  $(".containAnn").append( "<h3>Ajouter une annonce</h3>" );
  $(".modiP").hide();
  $(".modiP").append("<h3>Présentez vous</h3>");
});