package org.firmanmardiyanto.yourmate.home.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import org.firmanmardiyanto.yourmate.R
import org.firmanmardiyanto.yourmate.adapters.ArticleAdapter
import org.firmanmardiyanto.yourmate.adapters.PlaceAdapter
import org.firmanmardiyanto.yourmate.data.Resource
import org.firmanmardiyanto.yourmate.databinding.FragmentHomeBinding
import org.firmanmardiyanto.yourmate.domain.model.Article
import org.firmanmardiyanto.yourmate.domain.model.Place
import org.firmanmardiyanto.yourmate.item_decoration.SpacingItemDecoration
import org.firmanmardiyanto.yourmate.viewmodels.AuthViewModel

class HomeFragment : Fragment() {

//    private val authViewModel: AuthViewModel by activityViewModels()

    private val homeViewModel by lazy { ViewModelProvider(this).get(HomeViewModel::class.java) }

    private var _binding: FragmentHomeBinding? = null

    private val articleAdapter by lazy { ArticleAdapter() }
    private val placeAdapter by lazy { PlaceAdapter() }

    private val spacingItemDecoration by lazy { SpacingItemDecoration(orientation = SpacingItemDecoration.ORIENTATION.HORIZONTAL) }

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
        initArticle()
        initPlace()
    }

    private fun initUI() {
        with(binding) {
//            authViewModel.currentUser.observe(viewLifecycleOwner) {
//                when (it) {
//                    is Resource.Error -> Unit
//                    is Resource.Loading -> Unit
//                    is Resource.Success -> {
//                        Glide.with(requireContext())
//                            .load(it.data?.profileImage)
//                            .error(R.drawable.ic_image)
//                            .into(ivProfilePicture)
//                    }
//                }
//            }

            Glide.with(requireContext())
                .load("https://firebasestorage.googleapis.com/v0/b/yourmate-406e4.appspot.com/o/default-profile-picture.png?alt=media&token=7a104a65-ed48-44e3-9f90-f17442719416")
                .error(R.drawable.ic_image)
                .into(ivProfilePicture)

            with(rvPeople) {
                adapter = articleAdapter
                layoutManager =
                    LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
                addItemDecoration(spacingItemDecoration)
            }

            with(rvArticle) {
                adapter = articleAdapter
                layoutManager =
                    LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
                addItemDecoration(spacingItemDecoration)
            }

            with(rvPlace) {
                adapter = placeAdapter
                layoutManager =
                    LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
                addItemDecoration(spacingItemDecoration)
            }
        }
    }

    private fun initArticle() {
        articleAdapter.submitList(DUMMY_ARTICLE)
    }

    private fun initPlace() {
        placeAdapter.submitList(DUMMY_PLACE)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private val DUMMY_ARTICLE = listOf(
            Article(
                0,
                "Mental Sehat di Hari Senin Ini 5 Triknya",
                "https://d1vbn70lmn1nqe.cloudfront.net/prod/wp-content/uploads/2022/05/17090655/1.-Manfaat-Membaca-Buku-bagi-Kesehatan-Mental-01.jpg",
                "Bagi sebagian besar orang, memulai kembali hari senin mungkin menjadi rutinitas yang “menyeramkan”. Enggak heran, karena biasanya awal minggu akan ada banyak pekerjaan yang harus dilakukan. Apalagi buat kamu yang menghabiskan akhir pekan dengan liburan, kembali ke rutinitas seringkali terasa berat untuk dilakukan. Nah, agar kamu enggak terjebak di sindrom I Hate Monday, alangkah baiknya jika kamu melakukan trik khusus untuk mengatasinya. Yuk, cari tahu berikut ini: Melakukan “me time” dapat kamu lakukan pada akhir pekan untuk memanjakan diri sendiri. Memanjakan diri kamu dapat diartikan sebagai apresiasi atau memberikan penghargaan terhadap kerja keras dan rutinitas yang membosankan selama seminggu. Kamu dapat melakukannya dengan tidur siang, menghabiskan waktu untuk membaca buku, atau melakukan hal-hal yang kamu tidak dapat lakukan ketika bekerja, seperti perawatan di salon. Dengan menghabiskan waktu melalui kegiatan yang kamu suka, tentunya kamu dapat mengurangi stres akibat pekerjaan. Hal ini bertujuan agar ketika hari senin sudah datang kembali, mood dan semangat kamu sudah siap untuk menyambut awal minggu tersebut. Tentunya kamu juga dapat menghabiskan waktu bersama-sama dengan teman atau keluarga. Banyak kegiatan yang dapat dilakukan bersama orang-orang terdekat, seperti mencicipi menu di restoran baru, melakukan wisata kuliner, menonton film bersama, atau bahkan memasak bersama. Me time juga dapat menjaga kesehatan mental dan mencegah depresi akibat stres yang berlebihan, lho. Akhir pekan yang singkat jika tidak digunakan secara maksimal akan membuatmu menyesal. Untuk itu, waktu kosong antara hari sabtu dan minggu dapat kamu gunakan untuk membersihkan kamar tidur atau bahkan rumah. Kamar tidur bersih dan rapi tentunya memberikan perasaan nyaman yang dapat meningkatkan produktivitas. Selain itu, kamar yang nyaman akan membuat tidur lebih berkualitas. Hal ini bertujuan agar kamu tidur dengan nyenyak saat pergantian hari ke senin. Tidur yang nyenyak dan cukup, tentu akan membuat kamu bangun dengan segar dan meningkatkan semangat untuk menjalani aktivitas. Menjalani rutinitas mungkin membuatmu melakukan banyak kegiatan buruk. Nah, libur akhir pekan dapat kamu gunakan untuk mengonsumsi makanan sehat, dan olahraga. Kamu juga harus menjaga kesehatan untuk kembali memulai rutinitas ketika ketika senin datang. Selain itu, kamu juga dapat menyusun jadwal untuk melakukan berbagai kegiatan. Jadwal ini dapat kamu buat berdasarkan janji dan rencana kedepannya. Selain membuat pengelolaan waktu lebih baik, menyusun jadwal dapat membuat kamu lebih semangat ketika hari senin, karena membuatmu lebih terpacu untuk cepat melewati hari. Ketika hari senin tiba, kamu juga harus menyiapkan sarapan yang sehat dan lezat. Konsumsi makanan lezat seharusnya dapat meningkatkan suasana hatimu. Jangan melewatkan sarapan karena dapat menyebabkan makan siangmu menjadi lebih banyak. Makan terlalu banyak dapat menyebabkan rasa kantuk berlebihan yang dapat mengganggu semangatmu. Selain itu, makan terlalu banyak juga meningkatkan risiko untuk terserang diabetes dan juga obesitas. Terjebak macet dapat membuat suasana hati jadi berantakan. Untuk itu, kamu harus menghindari kemacetan dengan berangkat lebih awal. Memulai hari senin dengan berangkat lebih awal juga dinilai lebih sehat, karena kamu juga terhindar dari paparan polusi yang berlebihan karena suasana jalan masih sepi. Paparan polusi yang berlebihan dapat meningkatkan risiko kamu terserang berbagai penyakit pernapasan seperti ISPA atau gangguan pada kulit. Berangkat lebih awal juga sebaiknya dilakukan setiap hari, agar suasana hati tetap baik ketika sampai di tempat bekerja. Memulai hari senin juga dapat dilakukan dengan mendengarkan musik favorit. Kamu dapat mendengarkan musik yang kamu suka ketika sedang berangkat, maupun saat sudah berada di kantor. Musik dapat memberikan ketenangan pada pikiran, serta menjaga mood tetap baik. Jika kamu merasa pekerjaan atau lingkungan kerja semakin membuat stres, kamu dapat hubungi psikolog di Halodoc. Tidak sulit kok membuat hari senin terasa menyenangkan dan lebih baik untuk memulai hari kalau kamu tahu cara mengatasinya. "
            ),
            Article(
                0,
                "Mental Sehat di Hari Senin Ini 5 Triknya",
                "https://d1vbn70lmn1nqe.cloudfront.net/prod/wp-content/uploads/2022/05/17090655/1.-Manfaat-Membaca-Buku-bagi-Kesehatan-Mental-01.jpg",
                "Bagi sebagian besar orang, memulai kembali hari senin mungkin menjadi rutinitas yang “menyeramkan”. Enggak heran, karena biasanya awal minggu akan ada banyak pekerjaan yang harus dilakukan. Apalagi buat kamu yang menghabiskan akhir pekan dengan liburan, kembali ke rutinitas seringkali terasa berat untuk dilakukan. Nah, agar kamu enggak terjebak di sindrom I Hate Monday, alangkah baiknya jika kamu melakukan trik khusus untuk mengatasinya. Yuk, cari tahu berikut ini: Melakukan “me time” dapat kamu lakukan pada akhir pekan untuk memanjakan diri sendiri. Memanjakan diri kamu dapat diartikan sebagai apresiasi atau memberikan penghargaan terhadap kerja keras dan rutinitas yang membosankan selama seminggu. Kamu dapat melakukannya dengan tidur siang, menghabiskan waktu untuk membaca buku, atau melakukan hal-hal yang kamu tidak dapat lakukan ketika bekerja, seperti perawatan di salon. Dengan menghabiskan waktu melalui kegiatan yang kamu suka, tentunya kamu dapat mengurangi stres akibat pekerjaan. Hal ini bertujuan agar ketika hari senin sudah datang kembali, mood dan semangat kamu sudah siap untuk menyambut awal minggu tersebut. Tentunya kamu juga dapat menghabiskan waktu bersama-sama dengan teman atau keluarga. Banyak kegiatan yang dapat dilakukan bersama orang-orang terdekat, seperti mencicipi menu di restoran baru, melakukan wisata kuliner, menonton film bersama, atau bahkan memasak bersama. Me time juga dapat menjaga kesehatan mental dan mencegah depresi akibat stres yang berlebihan, lho. Akhir pekan yang singkat jika tidak digunakan secara maksimal akan membuatmu menyesal. Untuk itu, waktu kosong antara hari sabtu dan minggu dapat kamu gunakan untuk membersihkan kamar tidur atau bahkan rumah. Kamar tidur bersih dan rapi tentunya memberikan perasaan nyaman yang dapat meningkatkan produktivitas. Selain itu, kamar yang nyaman akan membuat tidur lebih berkualitas. Hal ini bertujuan agar kamu tidur dengan nyenyak saat pergantian hari ke senin. Tidur yang nyenyak dan cukup, tentu akan membuat kamu bangun dengan segar dan meningkatkan semangat untuk menjalani aktivitas. Menjalani rutinitas mungkin membuatmu melakukan banyak kegiatan buruk. Nah, libur akhir pekan dapat kamu gunakan untuk mengonsumsi makanan sehat, dan olahraga. Kamu juga harus menjaga kesehatan untuk kembali memulai rutinitas ketika ketika senin datang. Selain itu, kamu juga dapat menyusun jadwal untuk melakukan berbagai kegiatan. Jadwal ini dapat kamu buat berdasarkan janji dan rencana kedepannya. Selain membuat pengelolaan waktu lebih baik, menyusun jadwal dapat membuat kamu lebih semangat ketika hari senin, karena membuatmu lebih terpacu untuk cepat melewati hari. Ketika hari senin tiba, kamu juga harus menyiapkan sarapan yang sehat dan lezat. Konsumsi makanan lezat seharusnya dapat meningkatkan suasana hatimu. Jangan melewatkan sarapan karena dapat menyebabkan makan siangmu menjadi lebih banyak. Makan terlalu banyak dapat menyebabkan rasa kantuk berlebihan yang dapat mengganggu semangatmu. Selain itu, makan terlalu banyak juga meningkatkan risiko untuk terserang diabetes dan juga obesitas. Terjebak macet dapat membuat suasana hati jadi berantakan. Untuk itu, kamu harus menghindari kemacetan dengan berangkat lebih awal. Memulai hari senin dengan berangkat lebih awal juga dinilai lebih sehat, karena kamu juga terhindar dari paparan polusi yang berlebihan karena suasana jalan masih sepi. Paparan polusi yang berlebihan dapat meningkatkan risiko kamu terserang berbagai penyakit pernapasan seperti ISPA atau gangguan pada kulit. Berangkat lebih awal juga sebaiknya dilakukan setiap hari, agar suasana hati tetap baik ketika sampai di tempat bekerja. Memulai hari senin juga dapat dilakukan dengan mendengarkan musik favorit. Kamu dapat mendengarkan musik yang kamu suka ketika sedang berangkat, maupun saat sudah berada di kantor. Musik dapat memberikan ketenangan pada pikiran, serta menjaga mood tetap baik. Jika kamu merasa pekerjaan atau lingkungan kerja semakin membuat stres, kamu dapat hubungi psikolog di Halodoc. Tidak sulit kok membuat hari senin terasa menyenangkan dan lebih baik untuk memulai hari kalau kamu tahu cara mengatasinya. "
            ),
            Article(
                0,
                "Mental Sehat di Hari Senin Ini 5 Triknya",
                "https://d1vbn70lmn1nqe.cloudfront.net/prod/wp-content/uploads/2022/05/17090655/1.-Manfaat-Membaca-Buku-bagi-Kesehatan-Mental-01.jpg",
                "Bagi sebagian besar orang, memulai kembali hari senin mungkin menjadi rutinitas yang “menyeramkan”. Enggak heran, karena biasanya awal minggu akan ada banyak pekerjaan yang harus dilakukan. Apalagi buat kamu yang menghabiskan akhir pekan dengan liburan, kembali ke rutinitas seringkali terasa berat untuk dilakukan. Nah, agar kamu enggak terjebak di sindrom I Hate Monday, alangkah baiknya jika kamu melakukan trik khusus untuk mengatasinya. Yuk, cari tahu berikut ini: Melakukan “me time” dapat kamu lakukan pada akhir pekan untuk memanjakan diri sendiri. Memanjakan diri kamu dapat diartikan sebagai apresiasi atau memberikan penghargaan terhadap kerja keras dan rutinitas yang membosankan selama seminggu. Kamu dapat melakukannya dengan tidur siang, menghabiskan waktu untuk membaca buku, atau melakukan hal-hal yang kamu tidak dapat lakukan ketika bekerja, seperti perawatan di salon. Dengan menghabiskan waktu melalui kegiatan yang kamu suka, tentunya kamu dapat mengurangi stres akibat pekerjaan. Hal ini bertujuan agar ketika hari senin sudah datang kembali, mood dan semangat kamu sudah siap untuk menyambut awal minggu tersebut. Tentunya kamu juga dapat menghabiskan waktu bersama-sama dengan teman atau keluarga. Banyak kegiatan yang dapat dilakukan bersama orang-orang terdekat, seperti mencicipi menu di restoran baru, melakukan wisata kuliner, menonton film bersama, atau bahkan memasak bersama. Me time juga dapat menjaga kesehatan mental dan mencegah depresi akibat stres yang berlebihan, lho. Akhir pekan yang singkat jika tidak digunakan secara maksimal akan membuatmu menyesal. Untuk itu, waktu kosong antara hari sabtu dan minggu dapat kamu gunakan untuk membersihkan kamar tidur atau bahkan rumah. Kamar tidur bersih dan rapi tentunya memberikan perasaan nyaman yang dapat meningkatkan produktivitas. Selain itu, kamar yang nyaman akan membuat tidur lebih berkualitas. Hal ini bertujuan agar kamu tidur dengan nyenyak saat pergantian hari ke senin. Tidur yang nyenyak dan cukup, tentu akan membuat kamu bangun dengan segar dan meningkatkan semangat untuk menjalani aktivitas. Menjalani rutinitas mungkin membuatmu melakukan banyak kegiatan buruk. Nah, libur akhir pekan dapat kamu gunakan untuk mengonsumsi makanan sehat, dan olahraga. Kamu juga harus menjaga kesehatan untuk kembali memulai rutinitas ketika ketika senin datang. Selain itu, kamu juga dapat menyusun jadwal untuk melakukan berbagai kegiatan. Jadwal ini dapat kamu buat berdasarkan janji dan rencana kedepannya. Selain membuat pengelolaan waktu lebih baik, menyusun jadwal dapat membuat kamu lebih semangat ketika hari senin, karena membuatmu lebih terpacu untuk cepat melewati hari. Ketika hari senin tiba, kamu juga harus menyiapkan sarapan yang sehat dan lezat. Konsumsi makanan lezat seharusnya dapat meningkatkan suasana hatimu. Jangan melewatkan sarapan karena dapat menyebabkan makan siangmu menjadi lebih banyak. Makan terlalu banyak dapat menyebabkan rasa kantuk berlebihan yang dapat mengganggu semangatmu. Selain itu, makan terlalu banyak juga meningkatkan risiko untuk terserang diabetes dan juga obesitas. Terjebak macet dapat membuat suasana hati jadi berantakan. Untuk itu, kamu harus menghindari kemacetan dengan berangkat lebih awal. Memulai hari senin dengan berangkat lebih awal juga dinilai lebih sehat, karena kamu juga terhindar dari paparan polusi yang berlebihan karena suasana jalan masih sepi. Paparan polusi yang berlebihan dapat meningkatkan risiko kamu terserang berbagai penyakit pernapasan seperti ISPA atau gangguan pada kulit. Berangkat lebih awal juga sebaiknya dilakukan setiap hari, agar suasana hati tetap baik ketika sampai di tempat bekerja. Memulai hari senin juga dapat dilakukan dengan mendengarkan musik favorit. Kamu dapat mendengarkan musik yang kamu suka ketika sedang berangkat, maupun saat sudah berada di kantor. Musik dapat memberikan ketenangan pada pikiran, serta menjaga mood tetap baik. Jika kamu merasa pekerjaan atau lingkungan kerja semakin membuat stres, kamu dapat hubungi psikolog di Halodoc. Tidak sulit kok membuat hari senin terasa menyenangkan dan lebih baik untuk memulai hari kalau kamu tahu cara mengatasinya. "
            ),
            Article(
                0,
                "Mental Sehat di Hari Senin Ini 5 Triknya",
                "https://d1vbn70lmn1nqe.cloudfront.net/prod/wp-content/uploads/2022/05/17090655/1.-Manfaat-Membaca-Buku-bagi-Kesehatan-Mental-01.jpg",
                "Bagi sebagian besar orang, memulai kembali hari senin mungkin menjadi rutinitas yang “menyeramkan”. Enggak heran, karena biasanya awal minggu akan ada banyak pekerjaan yang harus dilakukan. Apalagi buat kamu yang menghabiskan akhir pekan dengan liburan, kembali ke rutinitas seringkali terasa berat untuk dilakukan. Nah, agar kamu enggak terjebak di sindrom I Hate Monday, alangkah baiknya jika kamu melakukan trik khusus untuk mengatasinya. Yuk, cari tahu berikut ini: Melakukan “me time” dapat kamu lakukan pada akhir pekan untuk memanjakan diri sendiri. Memanjakan diri kamu dapat diartikan sebagai apresiasi atau memberikan penghargaan terhadap kerja keras dan rutinitas yang membosankan selama seminggu. Kamu dapat melakukannya dengan tidur siang, menghabiskan waktu untuk membaca buku, atau melakukan hal-hal yang kamu tidak dapat lakukan ketika bekerja, seperti perawatan di salon. Dengan menghabiskan waktu melalui kegiatan yang kamu suka, tentunya kamu dapat mengurangi stres akibat pekerjaan. Hal ini bertujuan agar ketika hari senin sudah datang kembali, mood dan semangat kamu sudah siap untuk menyambut awal minggu tersebut. Tentunya kamu juga dapat menghabiskan waktu bersama-sama dengan teman atau keluarga. Banyak kegiatan yang dapat dilakukan bersama orang-orang terdekat, seperti mencicipi menu di restoran baru, melakukan wisata kuliner, menonton film bersama, atau bahkan memasak bersama. Me time juga dapat menjaga kesehatan mental dan mencegah depresi akibat stres yang berlebihan, lho. Akhir pekan yang singkat jika tidak digunakan secara maksimal akan membuatmu menyesal. Untuk itu, waktu kosong antara hari sabtu dan minggu dapat kamu gunakan untuk membersihkan kamar tidur atau bahkan rumah. Kamar tidur bersih dan rapi tentunya memberikan perasaan nyaman yang dapat meningkatkan produktivitas. Selain itu, kamar yang nyaman akan membuat tidur lebih berkualitas. Hal ini bertujuan agar kamu tidur dengan nyenyak saat pergantian hari ke senin. Tidur yang nyenyak dan cukup, tentu akan membuat kamu bangun dengan segar dan meningkatkan semangat untuk menjalani aktivitas. Menjalani rutinitas mungkin membuatmu melakukan banyak kegiatan buruk. Nah, libur akhir pekan dapat kamu gunakan untuk mengonsumsi makanan sehat, dan olahraga. Kamu juga harus menjaga kesehatan untuk kembali memulai rutinitas ketika ketika senin datang. Selain itu, kamu juga dapat menyusun jadwal untuk melakukan berbagai kegiatan. Jadwal ini dapat kamu buat berdasarkan janji dan rencana kedepannya. Selain membuat pengelolaan waktu lebih baik, menyusun jadwal dapat membuat kamu lebih semangat ketika hari senin, karena membuatmu lebih terpacu untuk cepat melewati hari. Ketika hari senin tiba, kamu juga harus menyiapkan sarapan yang sehat dan lezat. Konsumsi makanan lezat seharusnya dapat meningkatkan suasana hatimu. Jangan melewatkan sarapan karena dapat menyebabkan makan siangmu menjadi lebih banyak. Makan terlalu banyak dapat menyebabkan rasa kantuk berlebihan yang dapat mengganggu semangatmu. Selain itu, makan terlalu banyak juga meningkatkan risiko untuk terserang diabetes dan juga obesitas. Terjebak macet dapat membuat suasana hati jadi berantakan. Untuk itu, kamu harus menghindari kemacetan dengan berangkat lebih awal. Memulai hari senin dengan berangkat lebih awal juga dinilai lebih sehat, karena kamu juga terhindar dari paparan polusi yang berlebihan karena suasana jalan masih sepi. Paparan polusi yang berlebihan dapat meningkatkan risiko kamu terserang berbagai penyakit pernapasan seperti ISPA atau gangguan pada kulit. Berangkat lebih awal juga sebaiknya dilakukan setiap hari, agar suasana hati tetap baik ketika sampai di tempat bekerja. Memulai hari senin juga dapat dilakukan dengan mendengarkan musik favorit. Kamu dapat mendengarkan musik yang kamu suka ketika sedang berangkat, maupun saat sudah berada di kantor. Musik dapat memberikan ketenangan pada pikiran, serta menjaga mood tetap baik. Jika kamu merasa pekerjaan atau lingkungan kerja semakin membuat stres, kamu dapat hubungi psikolog di Halodoc. Tidak sulit kok membuat hari senin terasa menyenangkan dan lebih baik untuk memulai hari kalau kamu tahu cara mengatasinya. "
            ),
            Article(
                0,
                "Mental Sehat di Hari Senin Ini 5 Triknya",
                "https://d1vbn70lmn1nqe.cloudfront.net/prod/wp-content/uploads/2022/05/17090655/1.-Manfaat-Membaca-Buku-bagi-Kesehatan-Mental-01.jpg",
                "Bagi sebagian besar orang, memulai kembali hari senin mungkin menjadi rutinitas yang “menyeramkan”. Enggak heran, karena biasanya awal minggu akan ada banyak pekerjaan yang harus dilakukan. Apalagi buat kamu yang menghabiskan akhir pekan dengan liburan, kembali ke rutinitas seringkali terasa berat untuk dilakukan. Nah, agar kamu enggak terjebak di sindrom I Hate Monday, alangkah baiknya jika kamu melakukan trik khusus untuk mengatasinya. Yuk, cari tahu berikut ini: Melakukan “me time” dapat kamu lakukan pada akhir pekan untuk memanjakan diri sendiri. Memanjakan diri kamu dapat diartikan sebagai apresiasi atau memberikan penghargaan terhadap kerja keras dan rutinitas yang membosankan selama seminggu. Kamu dapat melakukannya dengan tidur siang, menghabiskan waktu untuk membaca buku, atau melakukan hal-hal yang kamu tidak dapat lakukan ketika bekerja, seperti perawatan di salon. Dengan menghabiskan waktu melalui kegiatan yang kamu suka, tentunya kamu dapat mengurangi stres akibat pekerjaan. Hal ini bertujuan agar ketika hari senin sudah datang kembali, mood dan semangat kamu sudah siap untuk menyambut awal minggu tersebut. Tentunya kamu juga dapat menghabiskan waktu bersama-sama dengan teman atau keluarga. Banyak kegiatan yang dapat dilakukan bersama orang-orang terdekat, seperti mencicipi menu di restoran baru, melakukan wisata kuliner, menonton film bersama, atau bahkan memasak bersama. Me time juga dapat menjaga kesehatan mental dan mencegah depresi akibat stres yang berlebihan, lho. Akhir pekan yang singkat jika tidak digunakan secara maksimal akan membuatmu menyesal. Untuk itu, waktu kosong antara hari sabtu dan minggu dapat kamu gunakan untuk membersihkan kamar tidur atau bahkan rumah. Kamar tidur bersih dan rapi tentunya memberikan perasaan nyaman yang dapat meningkatkan produktivitas. Selain itu, kamar yang nyaman akan membuat tidur lebih berkualitas. Hal ini bertujuan agar kamu tidur dengan nyenyak saat pergantian hari ke senin. Tidur yang nyenyak dan cukup, tentu akan membuat kamu bangun dengan segar dan meningkatkan semangat untuk menjalani aktivitas. Menjalani rutinitas mungkin membuatmu melakukan banyak kegiatan buruk. Nah, libur akhir pekan dapat kamu gunakan untuk mengonsumsi makanan sehat, dan olahraga. Kamu juga harus menjaga kesehatan untuk kembali memulai rutinitas ketika ketika senin datang. Selain itu, kamu juga dapat menyusun jadwal untuk melakukan berbagai kegiatan. Jadwal ini dapat kamu buat berdasarkan janji dan rencana kedepannya. Selain membuat pengelolaan waktu lebih baik, menyusun jadwal dapat membuat kamu lebih semangat ketika hari senin, karena membuatmu lebih terpacu untuk cepat melewati hari. Ketika hari senin tiba, kamu juga harus menyiapkan sarapan yang sehat dan lezat. Konsumsi makanan lezat seharusnya dapat meningkatkan suasana hatimu. Jangan melewatkan sarapan karena dapat menyebabkan makan siangmu menjadi lebih banyak. Makan terlalu banyak dapat menyebabkan rasa kantuk berlebihan yang dapat mengganggu semangatmu. Selain itu, makan terlalu banyak juga meningkatkan risiko untuk terserang diabetes dan juga obesitas. Terjebak macet dapat membuat suasana hati jadi berantakan. Untuk itu, kamu harus menghindari kemacetan dengan berangkat lebih awal. Memulai hari senin dengan berangkat lebih awal juga dinilai lebih sehat, karena kamu juga terhindar dari paparan polusi yang berlebihan karena suasana jalan masih sepi. Paparan polusi yang berlebihan dapat meningkatkan risiko kamu terserang berbagai penyakit pernapasan seperti ISPA atau gangguan pada kulit. Berangkat lebih awal juga sebaiknya dilakukan setiap hari, agar suasana hati tetap baik ketika sampai di tempat bekerja. Memulai hari senin juga dapat dilakukan dengan mendengarkan musik favorit. Kamu dapat mendengarkan musik yang kamu suka ketika sedang berangkat, maupun saat sudah berada di kantor. Musik dapat memberikan ketenangan pada pikiran, serta menjaga mood tetap baik. Jika kamu merasa pekerjaan atau lingkungan kerja semakin membuat stres, kamu dapat hubungi psikolog di Halodoc. Tidak sulit kok membuat hari senin terasa menyenangkan dan lebih baik untuk memulai hari kalau kamu tahu cara mengatasinya. "
            ),
        )

        private val DUMMY_PLACE = listOf(
            Place(
                0,
                "Kampoeng Kopi Banaran",
                "https://asset.kompas.com/crops/EQRjhFQ9UgwSXa-wJhIvR0ovItA=/0x0:0x0/750x500/data/photo/2021/04/01/60659fe5bbfaf.jpg",
                "Semarang",
                "Kampoeng Kopi Banaran, sebuah agro wisata perkebunan kopi di Kabupaten Semarang. Tempat wisata ini memiliki luas 462 hektar yang sebagian dijadikan resort dan tempat wisata. Lokasinya berada di Areal Perkebunan Kopi Kebun Getas Afdeling Assinan tepatnya Jl. Raya Semarang ? Solo Km. 35. Lokasi Kampoeng Kopi Banaran yang berada di ketinggian 480 ? 600m dpl membuat suhu udara disana sejuk antara 23?C ? 27?C. Jadi cocok banget buat pelesir mencari udara dingin dan segar dengan pemandangan indah. Menghilangkan penat kesibukan Kota besar, di tengah perkebunan yang asri.",
                4.3,
                "Taman Hiburan"
            ),
            Place(
                1,
                "Kampoeng Kopi Banaran",
                "https://asset.kompas.com/crops/EQRjhFQ9UgwSXa-wJhIvR0ovItA=/0x0:0x0/750x500/data/photo/2021/04/01/60659fe5bbfaf.jpg",
                "Semarang",
                "Kampoeng Kopi Banaran, sebuah agro wisata perkebunan kopi di Kabupaten Semarang. Tempat wisata ini memiliki luas 462 hektar yang sebagian dijadikan resort dan tempat wisata. Lokasinya berada di Areal Perkebunan Kopi Kebun Getas Afdeling Assinan tepatnya Jl. Raya Semarang ? Solo Km. 35. Lokasi Kampoeng Kopi Banaran yang berada di ketinggian 480 ? 600m dpl membuat suhu udara disana sejuk antara 23?C ? 27?C. Jadi cocok banget buat pelesir mencari udara dingin dan segar dengan pemandangan indah. Menghilangkan penat kesibukan Kota besar, di tengah perkebunan yang asri.",
                4.3,
                "Taman Hiburan"
            ),
            Place(
                2,
                "Kampoeng Kopi Banaran",
                "https://asset.kompas.com/crops/EQRjhFQ9UgwSXa-wJhIvR0ovItA=/0x0:0x0/750x500/data/photo/2021/04/01/60659fe5bbfaf.jpg",
                "Semarang",
                "Kampoeng Kopi Banaran, sebuah agro wisata perkebunan kopi di Kabupaten Semarang. Tempat wisata ini memiliki luas 462 hektar yang sebagian dijadikan resort dan tempat wisata. Lokasinya berada di Areal Perkebunan Kopi Kebun Getas Afdeling Assinan tepatnya Jl. Raya Semarang ? Solo Km. 35. Lokasi Kampoeng Kopi Banaran yang berada di ketinggian 480 ? 600m dpl membuat suhu udara disana sejuk antara 23?C ? 27?C. Jadi cocok banget buat pelesir mencari udara dingin dan segar dengan pemandangan indah. Menghilangkan penat kesibukan Kota besar, di tengah perkebunan yang asri.",
                4.3,
                "Taman Hiburan"
            ),
            Place(
                3,
                "Kampoeng Kopi Banaran",
                "https://asset.kompas.com/crops/EQRjhFQ9UgwSXa-wJhIvR0ovItA=/0x0:0x0/750x500/data/photo/2021/04/01/60659fe5bbfaf.jpg",
                "Semarang",
                "Kampoeng Kopi Banaran, sebuah agro wisata perkebunan kopi di Kabupaten Semarang. Tempat wisata ini memiliki luas 462 hektar yang sebagian dijadikan resort dan tempat wisata. Lokasinya berada di Areal Perkebunan Kopi Kebun Getas Afdeling Assinan tepatnya Jl. Raya Semarang ? Solo Km. 35. Lokasi Kampoeng Kopi Banaran yang berada di ketinggian 480 ? 600m dpl membuat suhu udara disana sejuk antara 23?C ? 27?C. Jadi cocok banget buat pelesir mencari udara dingin dan segar dengan pemandangan indah. Menghilangkan penat kesibukan Kota besar, di tengah perkebunan yang asri.",
                4.3,
                "Taman Hiburan"
            ),
            Place(
                4,
                "Kampoeng Kopi Banaran",
                "https://asset.kompas.com/crops/EQRjhFQ9UgwSXa-wJhIvR0ovItA=/0x0:0x0/750x500/data/photo/2021/04/01/60659fe5bbfaf.jpg",
                "Semarang",
                "Kampoeng Kopi Banaran, sebuah agro wisata perkebunan kopi di Kabupaten Semarang. Tempat wisata ini memiliki luas 462 hektar yang sebagian dijadikan resort dan tempat wisata. Lokasinya berada di Areal Perkebunan Kopi Kebun Getas Afdeling Assinan tepatnya Jl. Raya Semarang ? Solo Km. 35. Lokasi Kampoeng Kopi Banaran yang berada di ketinggian 480 ? 600m dpl membuat suhu udara disana sejuk antara 23?C ? 27?C. Jadi cocok banget buat pelesir mencari udara dingin dan segar dengan pemandangan indah. Menghilangkan penat kesibukan Kota besar, di tengah perkebunan yang asri.",
                4.3,
                "Taman Hiburan"
            ),
        )
    }
}