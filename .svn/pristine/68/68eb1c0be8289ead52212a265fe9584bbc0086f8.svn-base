$(function() {
	$(".notification").html("");
	$('#encoderPartenaire').hide();
	$('#soumettreDemande').hide();
	$('#encoderEtudiant').hide();

	$('#encoderEtudiantB').on('click', function etudiant() {
		$(".notification").html("");
		$('#encoderEtudiant').show();
		$('#encoderPartenaire').hide();
		$('#soumettreDemande').hide();
	});

	$('#encoderPartenaireB').on('click', function partenaire() {
		$(".notification").html("");
		$('#encoderEtudiant').hide();
		$('#encoderPartenaire').show();
		$('#soumettreDemande').hide();
	});

	$('#soumettreDemandeB').on('click', function demande() {
		$(".notification").html("");
		$('#soumettreDemande').show();
		$('#encoderEtudiant').hide();
		$('#encoderPartenaire').hide();
	});

	$('#envoiEncodEtu').on(
			'click',
			function() {
				var json = formToJson($("#formEtudiant"));
				$.ajax({
					url : "/app",
					type : "POST",
					data : "requete=encodEtudiant&form=" + JSON.stringify(json)
							+ "&date=" + $('#dateId').val(),
					success : function() {
						$(".notification").html(
								"<p>L'étudiant a été encodé avec succes<p>");
					},
					error : function(jqXHR, textStatus, errorThrown) {
						$(".notification").html(
								"<p>Une erreur est survenue: " + errorThrown
										+ "<p>");
					}
				});
				return false;
			});
	
	$('#envoiInscrPartenaire').on("click", function(){
		var json = formToJson($("#formPartenaire"));
				$.ajax({
					url : "/app",
					type : "POST",
					data : "requete=encodPartenaire&form=" + JSON.stringify(json),
					success : function() {
						$(".notification").html(
								"<p>L'étudiant a été encodé avec succes<p>");
					},
					error : function(jqXHR, textStatus, errorThrown) {
						$(".notification").html(
								"<p>Une erreur est survenue: " + errorThrown
										+ "<p>");
					}
				});
				return false;
	})

	function formToJson(form) {
		var data = {};

		form.find('input').each(function(i, value) {
			data[$(value).attr('name')] = $(value).val();
		});

		form.find('select').each(function(i, value) {
			data[$(value).attr('name')] = $(value).val();
		});

		form.find('input[type=radio]:checked').each(function(i, value) {
			data[$(value).attr('name')] = $(value).val();
		});
		return data;
	}
	;
});