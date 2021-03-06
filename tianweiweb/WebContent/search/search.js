/* http://www.kryogenix.org/code/browser/searchhi/ */
/* odified 20021006 to fix query string parsing and add case insensitivity */

function highlightWord(node, word) {
	// Iterate into this nodes childNodes
	if (node.hasChildNodes) {
		var hi_cn;

		for (hi_cn = 0; hi_cn < node.childNodes.length; hi_cn++) {

			highlightWord(node.childNodes[hi_cn], word);

		}

	}

	// And do this node itself

	if (node.nodeType == 3) { // text node

		tempNodeVal = node.nodeValue.toLowerCase();
		tempWordVal = word.toLowerCase();

		if (tempNodeVal.indexOf(tempWordVal) != -1) {

			pn = node.parentNode;

			if (pn.className != "searchword") {

				// word has not already been highlighted!

				nv = node.nodeValue;

				ni = tempNodeVal.indexOf(tempWordVal);

				// Create a load of replacement nodes

				before = document.createTextNode(nv.substr(0, ni));

				docWordVal = nv.substr(ni, word.length);

				after = document.createTextNode(nv.substr(ni + word.length));

				hiwordtext = document.createTextNode(docWordVal);

				hiword = document.createElement("span");

				hiword.className = "searchword";

				hiword.appendChild(hiwordtext);

				pn.insertBefore(before, node);

				pn.insertBefore(hiword, node);

				pn.insertBefore(after, node);

				pn.removeChild(node);

			}

		}

	}

}

function googleSearchHighlight() {

	if (!document.createElement)
		return;

	ref = document.referrer;

	if (ref.indexOf('?') == -1)
		return;

	qs = ref.substr(ref.indexOf('?') + 1);

	qsa = qs.split('&');

	for (i = 0; i < qsa.length; i++) {

		qsip = qsa[i].split('=');

		if (qsip.length == 1)
			continue;

		if (qsip[0] == 'q' || qsip[0] == 'p') { // q= for Google, p= for Yahoo

			words = unescape(decodeURIComponent(qsip[1].replace(/\+/g, ' ')))
					.split(/\s+/);

			for (w = 0; w < words.length; w++) {

				highlightWord(document.getElementsByTagName("body")[0],
						words[w]);

			}

		}

	}

}

window.onload = googleSearchHighlight;
