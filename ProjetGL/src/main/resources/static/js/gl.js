
$('.modifP').hover(function() {
	$('#mod').css('color', "#808080");
},function() {
$('#mod').css('color', 'white');
});


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

$(function() 
{
  $(".containVis").hide();
  $(".containVis").append( "<p> <font color='#00001a'> <b>Découvrir toutes les annonces en France</b></font> </p>" );
  $(".containAnn").hide();
  $(".containAnn").append( "<h3> <font color='#00001a'> <b>Ajouter une annonce</b></font> </h3>" );
});