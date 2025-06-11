package com.example.flaggame


import kotlin.random.Random

fun getRandomCountryCode(): String{
// Define your list of country codes here
    val countryCodes = setOf(
        "AD", "AE", "AF", "AG", "AI", "AL", "AM", "AO", "AQ", "AR",
        "AS", "AT", "AU", "AW", "AX", "AZ", "BA", "BB", "BD", "BE",
        "BF", "BG", "BH", "BI", "BJ", "BL", "BM", "BN", "BO", "BQ",
        "BR", "BS", "BT", "BW", "BY", "BZ", "CA", "CC", "CD",
        "CF", "CG", "CH", "CI", "CK", "CL", "CM", "CN", "CO", "CR",
        "CU", "CV", "CW", "CX", "CY", "CZ", "DE", "DJ", "DK", "DM",
        "DO", "DZ", "EC", "EE", "EG", "EH", "ER", "ES", "ET", "EU",
        "FI", "FJ", "FK", "FM", "FO", "FR", "GA", "GB-NIR",
        "GB-SCT", "GB", "GD", "GE", "GG", "GH", "GI",
        "GL", "GM", "GN", "GQ", "GR", "GS", "GT", "GU", "GW",
        "GY", "HK", "HM", "HN", "HR", "HT", "HU", "ID", "IE", "IL",
        "IM", "IN", "IQ", "IR", "IS", "IT", "JE", "JM", "JO",
        "JP", "KE", "KG", "KH", "KI", "KM", "KN", "KP", "KR", "KW",
        "KY", "KZ", "LA", "LB", "LC", "LI", "LK", "LR", "LS", "LT",
        "LU", "LV", "LY", "MA", "MC", "MD", "ME", "MF", "MG", "MH",
        "MK", "ML", "MM", "MN", "MO", "MP", "MQ", "MR", "MS", "MT",
        "MU", "MV", "MW", "MX", "MY", "MZ", "NA", "NE", "NF",
        "NG", "NI", "NL", "NO", "NP", "NR", "NU", "NZ", "OM", "PA",
        "PE", "PF", "PG", "PH", "PK", "PL", "PN", "PR", "PS",
        "PT", "PW", "PY", "QA",  "RO", "RS", "RU", "RW", "SA",
        "SB", "SC", "SD", "SE", "SG", "SI", "SJ", "SK", "SL",
        "SM", "SN", "SO", "SR", "SS", "ST", "SV", "SX", "SY", "SZ",
        "TC", "TD", "TG", "TH", "TJ", "TK", "TL", "TM", "TN",
        "TO", "TR", "TT", "TV", "TW", "TZ", "UA", "UG", "US",
        "UY", "UZ", "VA", "VC", "VE", "VG", "VI", "VN", "VU",
        "WS", "XK", "YE", "ZA", "ZM", "ZW"
    )
    val randomIndex = Random.nextInt(countryCodes.size)
    // Return a random country code from the set
    return countryCodes.elementAt(randomIndex)
}

val countries = mapOf(
    "AD" to "Andorra",
    "AE" to "United Arab Emirates",
    "AF" to "Afghanistan",
    "AG" to "Antigua and Barbuda",
    "AI" to "Anguilla",
    "AL" to "Albania",
    "AM" to "Armenia",
    "AO" to "Angola",
    "AQ" to "Antarctica",
    "AR" to "Argentina",
    "AS" to "American Samoa",
    "AT" to "Austria",
    "AU" to "Australia",
    "AW" to "Aruba",
    "AX" to "Åland Islands",
    "AZ" to "Azerbaijan",
    "BA" to "Bosnia",
    "BB" to "Barbados",
    "BD" to "Bangladesh",
    "BE" to "Belgium",
    "BF" to "Burkina Faso",
    "BG" to "Bulgaria",
    "BH" to "Bahrain",
    "BI" to "Burundi",
    "BJ" to "Benin",
    "BL" to "Saint Barthélemy",
    "BM" to "Bermuda",
    "BN" to "Brunei",
    "BO" to "Bolivia",
    "BQ" to "Caribbean Netherlands",
    "BR" to "Brazil",
    "BS" to "Bahamas",
    "BT" to "Bhutan",
    "BW" to "Botswana",
    "BY" to "Belarus",
    "BZ" to "Belize",
    "CA" to "Canada",
    "CC" to "Cocos (Keeling) Islands",
    "CD" to "Congo",
    "CF" to "Central African Republic",
    "CG" to "Republic of the Congo",
    "CH" to "Switzerland",
    "CI" to "Ivory Coast",
    "CK" to "Cook Islands",
    "CL" to "Chile",
    "CM" to "Cameroon",
    "CN" to "China",
    "CO" to "Colombia",
    "CR" to "Costa Rica",
    "CU" to "Cuba",
    "CV" to "Cape Verde",
    "CW" to "Curaçao",
    "CX" to "Christmas Island",
    "CY" to "Cyprus",
    "CZ" to "Czech Republic",
    "DE" to "Germany",
    "DJ" to "Djibouti",
    "DK" to "Denmark",
    "DM" to "Dominica",
    "DO" to "Dominican Republic",
    "DZ" to "Algeria",
    "EC" to "Ecuador",
    "EE" to "Estonia",
    "EG" to "Egypt",
    "EH" to "Western Sahara",
    "ER" to "Eritrea",
    "ES" to "Spain",
    "ET" to "Ethiopia",
    "EU" to "Europe",
    "FI" to "Finland",
    "FJ" to "Fiji",
    "FK" to "Falkland Islands",
    "FM" to "Micronesia",
    "FO" to "Faroe Islands",
    "FR" to "France",
    "GA" to "Gabon",
    "GB-ENG" to "England",
    "GB-NIR" to "Northern Ireland",
    "GB-SCT" to "Scotland",
    "UK" to "United Kingdom",
    "GD" to "Grenada",
    "GE" to "Georgia",
    "GG" to "Guernsey",
    "GH" to "Ghana",
    "GI" to "Gibraltar",
    "GL" to "Greenland",
    "GM" to "Gambia",
    "GN" to "Guinea",
    "GQ" to "Equatorial Guinea",
    "GR" to "Greece",
    "GS" to "South Georgia and the South Sandwich Islands",
    "GT" to "Guatemala",
    "GU" to "Guam",
    "GW" to "Guinea-Bissau",
    "GY" to "Guyana",
    "HK" to "Hong Kong",
    "HM" to "Heard Island and McDonald Islands",
    "HN" to "Honduras",
    "HR" to "Croatia",
    "HT" to "Haiti",
    "HU" to "Hungary",
    "ID" to "Indonesia",
    "IE" to "Ireland",
    "IL" to "Israel",
    "IM" to "Isle of Man",
    "IN" to "India",
    "IQ" to "Iraq",
    "IR" to "Iran",
    "IS" to "Iceland",
    "IT" to "Italy",
    "JE" to "Jersey",
    "JM" to "Jamaica",
    "JO" to "Jordan",
    "JP" to "Japan",
    "KE" to "Kenya",
    "KG" to "Kyrgyzstan",
    "KH" to "Cambodia",
    "KI" to "Kiribati",
    "KM" to "Comoros",
    "KN" to "Saint Kitts and Nevis",
    "KP" to "North Korea",
    "KR" to "South Korea",
    "KW" to "Kuwait",
    "KY" to "Cayman Islands",
    "KZ" to "Kazakhstan",
    "LA" to "Laos",
    "LB" to "Lebanon",
    "LC" to "Saint Lucia",
    "LI" to "Liechtenstein",
    "LK" to "Sri Lanka",
    "LR" to "Liberia",
    "LS" to "Lesotho",
    "LT" to "Lithuania",
    "LU" to "Luxembourg",
    "LV" to "Latvia",
    "LY" to "Libya",
    "MA" to "Morocco",
    "MC" to "Monaco",
    "MD" to "Moldova",
    "ME" to "Montenegro",
    "MF" to "Saint Martin",
    "MG" to "Madagascar",
    "MH" to "Marshall Islands",
    "MK" to "North Macedonia",
    "ML" to "Mali",
    "MM" to "Myanmar",
    "MN" to "Mongolia",
    "MO" to "Macao",
    "MP" to "Northern Mariana Islands",
    "MQ" to "Martinique",
    "MR" to "Mauritania",
    "MS" to "Montserrat",
    "MT" to "Malta",
    "MU" to "Mauritius",
    "MV" to "Maldives",
    "MW" to "Malawi",
    "MX" to "Mexico",
    "MY" to "Malaysia",
    "MZ" to "Mozambique",
    "NA" to "Namibia",
    "NC" to "New Caledonia",
    "NE" to "Niger",
    "NF" to "Norfolk Island",
    "NG" to "Nigeria",
    "NI" to "Nicaragua",
    "NL" to "Netherlands",
    "NO" to "Norway",
    "NP" to "Nepal",
    "NR" to "Nauru",
    "NU" to "Niue",
    "NZ" to "New Zealand",
    "OM" to "Oman",
    "PA" to "Panama",
    "PE" to "Peru",
    "PF" to "French Polynesia",
    "PG" to "Papua New Guinea",
    "PH" to "Philippines",
    "PK" to "Pakistan",
    "PL" to "Poland",
    "PN" to "Pitcairn",
    "PR" to "Puerto Rico",
    "PS" to "Palestine",
    "PT" to "Portugal",
    "PW" to "Palau",
    "PY" to "Paraguay",
    "QA" to "Qatar",
    "RO" to "Romania",
    "RS" to "Serbia",
    "RU" to "Russia",
    "RW" to "Rwanda",
    "SA" to "Saudi Arabia",
    "SB" to "Solomon Islands",
    "SC" to "Seychelles",
    "SD" to "Sudan",
    "SE" to "Sweden",
    "SG" to "Singapore",
    "SI" to "Slovenia",
    "SJ" to "Svalbard and Jan Mayen Islands",
    "SK" to "Slovakia",
    "SL" to "Sierra Leone",
    "SM" to "San Marino",
    "SN" to "Senegal",
    "SO" to "Somalia",
    "SR" to "Suriname",
    "SS" to "South Sudan",
    "ST" to "Sao Tome and Principe",
    "SV" to "El Salvador",
    "SX" to "Sint Maarten",
    "SY" to "Syrian Arab Republic",
    "SZ" to "Swaziland",
    "TC" to "Turks and Caicos Islands",
    "TD" to "Chad",
    "TG" to "Togo",
    "TH" to "Thailand",
    "TJ" to "Tajikistan",
    "TK" to "Tokelau",
    "TL" to "Timor-Leste",
    "TM" to "Turkmenistan",
    "TN" to "Tunisia",
    "TO" to "Tonga",
    "TR" to "Turkey",
    "TT" to "Trinidad and Tobago",
    "TV" to "Tuvalu",
    "TW" to "Taiwan",
    "TZ" to "Tanzania",
    "UA" to "Ukraine",
    "UG" to "Uganda",
    "US" to "United States",
    "UY" to "Uruguay",
    "UZ" to "Uzbekistan",
    "VA" to "Holy See (Vatican City State)",
    "VC" to "Saint Vincent and the Grenadines",
    "VE" to "Venezuela",
    "VG" to "Virgin Islands, British",
    "VI" to "Virgin Islands, U.S.",
    "VN" to "Vietnam",
    "VU" to "Vanuatu",
    "WS" to "Samoa",
    "XK" to "Kosovo",
    "YE" to "Yemen",
    "ZA" to "South Africa",
    "ZM" to "Zambia",
    "ZW" to "Zimbabwe"
)

fun getImageResource(countryCode: String): Int? {
    return when (countryCode) {
        "AD" -> R.drawable.ad
        "AE" -> R.drawable.ae
        "AF" -> R.drawable.af
        "AG" -> R.drawable.ag
        "AI" -> R.drawable.ai
        "AL" -> R.drawable.al
        "AM" -> R.drawable.am
        "AO" -> R.drawable.ao
        "AQ" -> R.drawable.aq
        "AR" -> R.drawable.ar
        "AS" -> R.drawable.`as`
        "AT" -> R.drawable.at
        "AU" -> R.drawable.au
        "AW" -> R.drawable.aw
        "AX" -> R.drawable.ax
        "AZ" -> R.drawable.az
        "BA" -> R.drawable.ba
        "BB" -> R.drawable.bb
        "BD" -> R.drawable.bd
        "BE" -> R.drawable.be
        "BF" -> R.drawable.bf
        "BG" -> R.drawable.bg
        "BH" -> R.drawable.bh
        "BI" -> R.drawable.bi
        "BJ" -> R.drawable.bj
        "BL" -> R.drawable.bl
        "BM" -> R.drawable.bm
        "BN" -> R.drawable.bn
        "BO" -> R.drawable.bo
        "BQ" -> R.drawable.bq
        "BR" -> R.drawable.br
        "BS" -> R.drawable.bs
        "BT" -> R.drawable.bt
        "BW" -> R.drawable.bw
        "BY" -> R.drawable.by
        "BZ" -> R.drawable.bz
        "CA" -> R.drawable.ca
        "CC" -> R.drawable.cc
        "CD" -> R.drawable.cd
        "CF" -> R.drawable.cf
        "CG" -> R.drawable.cg
        "CH" -> R.drawable.ch
        "CI" -> R.drawable.ci
        "CK" -> R.drawable.ck
        "CL" -> R.drawable.cl
        "CM" -> R.drawable.cm
        "CN" -> R.drawable.cn
        "CO" -> R.drawable.co
        "CR" -> R.drawable.cr
        "CU" -> R.drawable.cu
        "CV" -> R.drawable.cv
        "CW" -> R.drawable.cw
        "CX" -> R.drawable.cx
        "CY" -> R.drawable.cy
        "CZ" -> R.drawable.cz
        "DE" -> R.drawable.de
        "DJ" -> R.drawable.dj
        "DK" -> R.drawable.dk
        "DM" -> R.drawable.dm
        "DO" -> R.drawable.resource_do
        "DZ" -> R.drawable.dz
        "EC" -> R.drawable.ec
        "EE" -> R.drawable.ee
        "EG" -> R.drawable.eg
        "EH" -> R.drawable.eh
        "ER" -> R.drawable.er
        "ES" -> R.drawable.es
        "ET" -> R.drawable.et
        "EU" -> R.drawable.eu
        "FI" -> R.drawable.fi
        "FJ" -> R.drawable.fj
        "FK" -> R.drawable.fk
        "FM" -> R.drawable.fm
        "FO" -> R.drawable.fo
        "FR" -> R.drawable.fr
        "GA" -> R.drawable.ga
        "UK" -> R.drawable.uk
        "GB-ENG" -> R.drawable.gb_eng
        "GB-NIR" -> R.drawable.gb_nir
        "GB-SCT" -> R.drawable.gb_sct
        "GD" -> R.drawable.gd
        "GE" -> R.drawable.ge
        "GG" -> R.drawable.gg
        "GH" -> R.drawable.gh
        "GI" -> R.drawable.gi
        "GL" -> R.drawable.gl
        "GM" -> R.drawable.gm
        "GN" -> R.drawable.gn
        "GQ" -> R.drawable.gq
        "GR" -> R.drawable.gr
        "GS" -> R.drawable.gs
        "GT" -> R.drawable.gt
        "GU" -> R.drawable.gu
        "GW" -> R.drawable.gw
        "GY" -> R.drawable.gy
        "HK" -> R.drawable.hk
        "HM" -> R.drawable.hm
        "HN" -> R.drawable.hn
        "HR" -> R.drawable.hr
        "HT" -> R.drawable.ht
        "HU" -> R.drawable.hu
        "ID" -> R.drawable.id
        "IE" -> R.drawable.ie
        "IL" -> R.drawable.il
        "IM" -> R.drawable.im
        "IN" -> R.drawable.`in`
        "IQ" -> R.drawable.iq
        "IR" -> R.drawable.ir
        "IS" -> R.drawable.`is`
        "IT" -> R.drawable.it
        "JE" -> R.drawable.je
        "JM" -> R.drawable.jm
        "JO" -> R.drawable.jo
        "JP" -> R.drawable.jp
        "KE" -> R.drawable.ke
        "KG" -> R.drawable.kg
        "KH" -> R.drawable.kh
        "KI" -> R.drawable.ki
        "KM" -> R.drawable.km
        "KN" -> R.drawable.kn
        "KP" -> R.drawable.kp
        "KR" -> R.drawable.kr
        "KW" -> R.drawable.kw
        "KY" -> R.drawable.ky
        "KZ" -> R.drawable.kz
        "LA" -> R.drawable.la
        "LB" -> R.drawable.lb
        "LC" -> R.drawable.lc
        "LI" -> R.drawable.li
        "LK" -> R.drawable.lk
        "LR" -> R.drawable.lr
        "LS" -> R.drawable.ls
        "LT" -> R.drawable.lt
        "LU" -> R.drawable.lu
        "LV" -> R.drawable.lv
        "LY" -> R.drawable.ly
        "MA" -> R.drawable.ma
        "MC" -> R.drawable.mc
        "MD" -> R.drawable.md
        "ME" -> R.drawable.me
        "MF" -> R.drawable.mf
        "MG" -> R.drawable.mg
        "MH" -> R.drawable.mh
        "MK" -> R.drawable.mk
        "ML" -> R.drawable.ml
        "MM" -> R.drawable.mm
        "MN" -> R.drawable.mn
        "MO" -> R.drawable.mo
        "MP" -> R.drawable.mp
        "MQ" -> R.drawable.mq
        "MR" -> R.drawable.mr
        "MS" -> R.drawable.ms
        "MT" -> R.drawable.mt
        "MU" -> R.drawable.mu
        "MV" -> R.drawable.mv
        "MW" -> R.drawable.mw
        "MX" -> R.drawable.mx
        "MY" -> R.drawable.my
        "MZ" -> R.drawable.mz
        "NA" -> R.drawable.na
        "NE" -> R.drawable.ne
        "NF" -> R.drawable.nf
        "NG" -> R.drawable.ng
        "NI" -> R.drawable.ni
        "NL" -> R.drawable.nl
        "NO" -> R.drawable.no
        "NP" -> R.drawable.np
        "NR" -> R.drawable.nr
        "NU" -> R.drawable.nu
        "NZ" -> R.drawable.nz
        "OM" -> R.drawable.om
        "PA" -> R.drawable.pa
        "PE" -> R.drawable.pe
        "PF" -> R.drawable.pf
        "PG" -> R.drawable.pg
        "PH" -> R.drawable.ph
        "PK" -> R.drawable.pk
        "PL" -> R.drawable.pl
        "PN" -> R.drawable.pn
        "PR" -> R.drawable.pr
        "PS" -> R.drawable.ps
        "PT" -> R.drawable.pt
        "PW" -> R.drawable.pw
        "PY" -> R.drawable.py
        "QA" -> R.drawable.qa
        "RO" -> R.drawable.ro
        "RS" -> R.drawable.rs
        "RU" -> R.drawable.ru
        "RW" -> R.drawable.rw
        "SA" -> R.drawable.sa
        "SB" -> R.drawable.sb
        "SC" -> R.drawable.sc
        "SD" -> R.drawable.sd
        "SE" -> R.drawable.se
        "SG" -> R.drawable.sg
        "SI" -> R.drawable.si
        "SJ" -> R.drawable.sj
        "SK" -> R.drawable.sk
        "SL" -> R.drawable.sl
        "SM" -> R.drawable.sm
        "SN" -> R.drawable.sn
        "SO" -> R.drawable.so
        "SR" -> R.drawable.sr
        "SS" -> R.drawable.ss
        "ST" -> R.drawable.st
        "SV" -> R.drawable.sv
        "SX" -> R.drawable.sx
        "SY" -> R.drawable.sy
        "SZ" -> R.drawable.sz
        "TC" -> R.drawable.tc
        "TD" -> R.drawable.td
        "TG" -> R.drawable.tg
        "TH" -> R.drawable.th
        "TJ" -> R.drawable.tj
        "TK" -> R.drawable.tk
        "TL" -> R.drawable.tl
        "TM" -> R.drawable.tm
        "TN" -> R.drawable.tn
        "TO" -> R.drawable.to
        "TR" -> R.drawable.tr
        "TT" -> R.drawable.tt
        "TV" -> R.drawable.tv
        "TW" -> R.drawable.tw
        "TZ" -> R.drawable.tz
        "UA" -> R.drawable.ua
        "UG" -> R.drawable.ug
        "US" -> R.drawable.us
        "UY" -> R.drawable.uy
        "UZ" -> R.drawable.uz
        "VA" -> R.drawable.va
        "VC" -> R.drawable.vc
        "VE" -> R.drawable.ve
        "VG" -> R.drawable.vg
        "VI" -> R.drawable.vi
        "VN" -> R.drawable.vn
        "VU" -> R.drawable.vu
        "WS" -> R.drawable.ws
        "XK" -> R.drawable.xk
        "YE" -> R.drawable.ye
        "ZA" -> R.drawable.za
        "ZM" -> R.drawable.zm
        "ZW" -> R.drawable.zw
        else -> null
    }
}
