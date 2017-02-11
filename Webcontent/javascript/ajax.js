var typeCompte = 0;
var id = 'encoderEtudiant';

$(function() {
	$(document).ready(function() {
		$.ajax({
			url : "/app",
			type : "POST",
			data : "requete=testConnect",
			success : function() {
				window.location.replace("/Webcontent/page.html");
			},
			error : function(jqXHR, textStatus, errorThrown) {
			}
		});
		return false;
	});

	var user = null; // variable global -> user connecté
	$('#divDemandes').hide();
	$("#inscription").hide();
	$('#menuEtudiant').hide();
	$('#Rechercher').hide();
	$('#encoderPartenaire').hide();
	$('#soumettreDemande').hide();
	$("#envoiInscr")
			.on(
					"click",
					function() {
						// On vérifie si les deux mots de passes entrés sont
						// identique
						var t = [ "nomId", "prenomId", "emailId", "pseudoInscr" ];
						var champVide = checkIfEmpty(t);
						console.log(champVide);
						if ($("#mdp").val() != $("#mdp2").val()) {
							$(".notification")
									.html(
											"<p>Les deux mots de passes ne sont pas identiques</p>");

							// S'il le sont on envoi le formulaire a la servlet
						}

						else if (!champVide) {
							var json = formToJson($("#formInscr"));
							$
									.ajax({
										url : "/app",
										type : "POST",
										data : "requete=inscription&form="
												+ JSON.stringify(json),
										success : function() {
											window.location
													.replace("/Webcontent/page.html");
										},
										error : function(jqXHR, textStatus,
												errorThrown) {
											$(".notification")
													.html(
															"<p>L'inscription à ratée: "
																	+ jqXHR.responseText
																	+ "<p>");
										}
									});
						} else {
							$(".notification").html(
									"<p>Veuillez remplir tous les champs</p>");
						}
						;
						return false;
					});

	$("#envoiAuth").on(
			"click",
			function() {
				console.log("hello");
				$.ajax({
					url : "/app",
					type : "POST",
					data : "requete=authentifie&pseudo=" + $("#pseudo").val()
							+ "&mdp=" + $("#password").val(),
					success : function(reponse) {
						user = JSON.parse(reponse);
						typeCompte = 1;
						window.location.replace("/Webcontent/page.html");
						menuCompte();
					},
					error : function(jqXHR, textStatus, errorThrown) {
						$(".notification").html(
								"<p>La connection à ratée: "
										+ jqXHR.responseText + "<p>");
					}
				});
				return false;
			});

	$("#inscr").on("click", function() {
		$("#connexion").hide();
		$("#inscription").show();
		$('title').html('inscription');
		return false;
	});

	function formToJson(form) {
		var data = {};

		form.find('input').each(function(i, value) {
			data[$(value).attr('name')] = $(value).val();
		});

		form.find('select').each(function(i, value) {
			data[$(value).attr('name')] = $(value).val();
		});

		return data;
	}
	;

	function checkIfEmpty(t) {
		var champVide = false;
		for (var i = 0; i < t.length; i++) {
			console.log($("#" + t[i]).val());
			if (!$("#" + t[i]).val()) {
				champVide = true;
				document.getElementById(t[i]).style.borderColor = "#FF0000";
			}
		}
		return champVide;
	}
});
