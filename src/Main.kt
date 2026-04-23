class Pelanggan(
    val nama: String,
    saldoAwal: Int
) {
    var saldo: Int = saldoAwal
        private set

    fun kurangiSaldo(jumlah: Int): Boolean {
        return if (saldo >= jumlah) {
            saldo -= jumlah
            true
        } else {
            false
        }
    }
}

class MesinPrint(
    tintaAwal: Int,
    kertasAwal: Int,
    val hargaPerLembar: Int
) {
    var tinta: Int = tintaAwal
        private set

    var kertas: Int = kertasAwal
        private set

    fun cetak(pelanggan: Pelanggan, jumlahHalaman: Int) {
        val totalHarga = jumlahHalaman * hargaPerLembar

        println("Mencoba mencetak $jumlahHalaman halaman...")

        if (tinta <= 0 || kertas <= 0) {
            println("❌ Gagal: Tinta atau kertas habis")
            return
        }

        if (jumlahHalaman > kertas) {
            println("❌ Gagal: Kertas tidak cukup")
            return
        }

        if (!pelanggan.kurangiSaldo(totalHarga)) {
            println("❌ Gagal: Saldo tidak cukup")
            return
        }

        tinta -= jumlahHalaman
        kertas -= jumlahHalaman

        println("✅ Berhasil mencetak")
        println("Sisa saldo: ${pelanggan.saldo}")
        println("Sisa tinta: $tinta")
        println("Sisa kertas: $kertas")
    }
}

fun main() {
    val pelanggan = Pelanggan("Vera", 5000)
    val mesin = MesinPrint(10, 10, 1000)

    println("=== SIMULASI GAGAL ===")
    mesin.cetak(pelanggan, 10) // saldo kurang

    println("\n=== SIMULASI SUKSES ===")
    val pelanggan2 = Pelanggan("Nur", 20000)
    mesin.cetak(pelanggan2, 5)
}