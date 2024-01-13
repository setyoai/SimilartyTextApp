package com.setyo.similartytextapp.ui.similarty

class AlgorithmRatcliffObershelp {
    fun similarity(s1: String, s2: String): Double {

//        Pengecekan Kesamaan Awal:
        if (s1 == s2) {
            return 1.0
        }
//        Mendapatkan Daftar Cocokan:
        val matches = getMatchList(s1, s2)

//        Menghitung Total Panjang Cocokan:
        var sumOfMatches = 0

        for (match in matches) {
            sumOfMatches += match.length
        }

//        Menghitung dan Mengembalikan Skor Kesamaan:
        return 2.0 * sumOfMatches / (s1.length + s2.length)
    }

    private fun getMatchList(s1: String, s2: String): List<String> {

//        Inisialisasi List dan Pencarian Cocokan Utama:
        val list = mutableListOf<String>()
        val match = frontMaxMatch(s1, s2)

//        Pengecekan Jika Ada Cocokan:
        if (match.isNotEmpty()) {
//            Pembagian String dan Rekursi:
            val frontSource = s1.substring(0, s1.indexOf(match))
            val frontTarget = s2.substring(0, s2.indexOf(match))
            val frontQueue = getMatchList(frontSource, frontTarget)

            val endSource = s1.substring(s1.indexOf(match) + match.length)
            val endTarget = s2.substring(s2.indexOf(match) + match.length)
            val endQueue = getMatchList(endSource, endTarget)

//        Penambahan ke Daftar dan Penggabungan Hasil Rekursi:
            list.add(match)
            list.addAll(frontQueue)
            list.addAll(endQueue)
        }

        return list
    }

    private fun frontMaxMatch(s1: String, s2: String): String {
//        Inisialisasi Variabel Penyimpanan:
        var longest = 0
        var longestSubstring = ""

//        Iterasi Melalui Indeks s1:
        for (i in s1.indices) {

//            Iterasi Melalui Panjang Substring:
            for (j in i + 1..s1.length) {
                
//                Mengambil dan Memeriksa Substring:
                val substring = s1.substring(i, j)
                if (s2.contains(substring) && substring.length > longest) {
                    longest = substring.length
                    longestSubstring = substring
                }
            }
        }
        return longestSubstring
    }
}