```markdown
# Coran — Kur'ân-ı Kerim (Arapça Metin)

[![License: GPL v3](https://img.shields.io/badge/License-GPLv3-blue.svg)](https://www.gnu.org/licenses/gpl-3.0)
[![Android](https://img.shields.io/badge/Platform-Android-green.svg)](https://www.android.com/)
[![Java](https://img.shields.io/badge/Language-Java-orange.svg)](https://www.java.com/)

**Coran**, Kur'ân-ı Kerim'in Arapça metnini (harekesiz) Android cihazlarda okumak için
geliştirilmiş, ücretsiz ve açık kaynak bir uygulamadır.

> **Not:** README.md, DeepSeek yapay zekası tarafından yazılmıştır. DeepSeek yapay zekası,
> uygulamanın son kontrol ve düzeltmelerinde büyük katkı sağlamıştır.

---

## 📖 Türkçe

### Hakkında

Bu uygulama, Kur'ân-ı Kerim'in **harekesiz Arapça metnini** görüntüler.
Harekesiz metin olduğu için, doğru okuyabilmek için **ileri düzey Arapça bilgisi**
veya **hafızlık** gerektirir. Bu, uygulamanın bir eksiği değil, bilinçli bir
tercihtir — metin orijinal mushaf düzenine sadık kalır.

Uygulama tamamen **çevrimdışı** çalışır. Metin, uygulamanın içine gömülüdür;
internet bağlantısı gerektirmez, hiçbir veri toplamaz, hiçbir sunucuya bağlanmaz.

### Özellikler

- 📚 **114 sure**, tamamı Arapça metin olarak gömülü (Java sınıfları halinde)
- 🔤 **Font desteği** — varsayılan sistem fontu veya kullanıcının kendi `.ttf` dosyası
- 🎨 **Renk özelleştirme** — arka plan ve yazı rengi (`#AARRGGBB` formatında)
- ✍️ **Yazı stili** — normal, kalın, italik, kalın-italik
- 📏 **Yazı boyutu** — sp cinsinden ayarlanabilir
- 🧭 **Kaldığın yerden devam** — sure numarası, satır ve tüm ayarlar otomatik kaydedilir
- 📜 **Özel kaydırma çubuğu** — metin içinde rahat gezinme ve sürükleme
- 💾 **Hafızlık (hıfz) desteği** — birden fazla sureyi tek dosyada birleştirme
- 🌐 **Tamamen çevrimdışı** — internet izni yok, veri toplama yok

### Ekran Görüntüsü

*(Buraya ekran görüntüsü eklenebilir)*

### Kurulum

#### APK olarak

1. Releases sayfasından en son APK'yı indirin
2. Telefonda "bilinmeyen kaynaklardan yükleme" iznini verin
3. APK'yı yükleyin

#### Kaynaktan derleme

```bash
git clone https://github.com/muratsivas76/Coran.git
cd Coran
./gradlew assembleDebug
```

APK: `app/build/outputs/apk/debug/app-debug.apk`

### Kullanım

1. Uygulama açıldığında **sure numarasını** girin (1–114)
2. **Show** butonuna basın — sure görüntülenir
3. **Settings** butonundan font, renk, boyut ve stili ayarlayın
4. **Help** butonundan sure listesini görün
5. **Hifz** butonundan birden fazla sureyi tek dosyada birleştirin (örn. `2-5`)
6. **Exit** butonuyla çıkın — tüm ayarlar otomatik kaydedilir

### Veri Konumu

Uygulama, ayarları ve kayıtları şurada saklar:

```
/sdcard/Download/carets/
├── kuran.txt          # Ayarlar ve son okuma konumu
├── ttf/               # Kullanıcı fontları (.ttf)
└── suver/             # Hıfz için birleştirilmiş sureler
```

`kuran.txt` formatı:

```
SureNumarası:SatırNumarası:FontBoyutu:FontStili:FontAdı:ArkaplanRengi:YazıRengi
```

Örnek:

```
1:0:28.0F:0:Default:#FFFFFFFF:#FF000000
```

### Katkıda Bulunma

Katkılar memnuniyetle karşılanır. Özellikle:

- **Harekeli metin desteği** — `net.murat.sures` paketindeki `Sure_XXX.java`
  sınıflarını harekeli metinle yeniden yazıp derleyebilirsiniz
- **Yeni diller** — sure isimleri çevirisi
- **Hata bildirimleri** — issue açın

### Lisans

Bu proje **GNU General Public License v3.0** ile lisanslanmıştır.
Detaylar için [LICENSE](LICENSE) dosyasına bakın.

### Geliştirici

**Murat İnan** (Sivas, Türkiye)
- GitHub: [@muratsivas76](https://github.com/muratsivas76)
- E-posta: muratsivas76@gmail.com

---

## 📖 English

### About

**Coran** is a free, open-source Android application for reading the
**Arabic text of the Holy Quran (without diacritics / harakat)**.

Since the text is without diacritics, **advanced Arabic knowledge** or
**memorization (hifz)** is required to read it correctly. This is a
deliberate design choice, not a limitation — the text follows the original
mushaf layout faithfully.

The app works **fully offline**. The text is embedded in the application;
it does not require an internet connection, does not collect any data,
and does not connect to any server.

> **Note:** README.md was written by DeepSeek AI. DeepSeek AI made a major
> contribution to the final review and corrections of the application.

### Features

- 📚 **114 surahs**, fully embedded as Arabic text (as Java classes)
- 🔤 **Font support** — default system font or user-provided `.ttf`
- 🎨 **Color customization** — background and foreground colors (`#AARRGGBB`)
- ✍️ **Text style** — normal, bold, italic, bold-italic
- 📏 **Font size** — adjustable in sp
- 🧭 **Resume where you left off** — surah number, line and all settings saved
- 📜 **Custom scrollbar** — smooth scrolling and draggable thumb
- 💾 **Hifz support** — merge multiple surahs into a single file
- 🌐 **Fully offline** — no internet permission, no data collection

### Screenshot

*(Screenshot can be added here)*

### Installation

#### As APK

1. Download the latest APK from the Releases page
2. Enable "install from unknown sources" on your phone
3. Install the APK

#### Build from source

```bash
git clone https://github.com/muratsivas76/Coran.git
cd Coran
./gradlew assembleDebug
```

APK: `app/build/outputs/apk/debug/app-debug.apk`

### Usage

1. Enter the **surah number** (1–114) when the app opens
2. Press **Show** — the surah is displayed
3. Use **Settings** to adjust font, color, size and style
4. Use **Help** to view the surah list
5. Use **Hifz** to merge multiple surahs into one file (e.g. `2-5`)
6. Press **Exit** — all settings are saved automatically

### Data Location

The app stores settings and records here:

```
/sdcard/Download/carets/
├── kuran.txt          # Settings and last reading position
├── ttf/               # User fonts (.ttf)
└── suver/             # Merged surahs for hifz
```

`kuran.txt` format:

```
SurahNumber:LineNumber:FontSize:FontStyle:FontName:BGColor:FGColor
```

Example:

```
1:0:28.0F:0:Default:#FFFFFFFF:#FF000000
```

### Contributing

Contributions are welcome. Especially:

- **Diacritics (harakat) support** — rewrite the `Sure_XXX.java` classes in
  the `net.murat.sures` package with diacritics and rebuild
- **New languages** — surah name translations
- **Bug reports** — open an issue

### License

This project is licensed under the **GNU General Public License v3.0**.
See the [LICENSE](LICENSE) file for details.

### Author

**Murat İnan** (Sivas, Türkiye)
- GitHub: [@muratsivas76](https://github.com/muratsivas76)
- Email: muratsivas76@gmail.com

---

## ⚠️ Yasal Uyarı / Legal Notice

Bu uygulama, Kur'ân-ı Kerim'in **Arapça metnini** içerir. Metnin doğruluğu
konusunda azami özen gösterilmiştir; ancak **hiçbir dijital kopya, basılı
mushafın yerini tutmaz**. İbadet ve ezber için basılı mushaf esas alınmalıdır.

This application contains the **Arabic text** of the Holy Quran. While utmost
care has been taken regarding the accuracy of the text, **no digital copy can
replace a printed mushaf**. For worship and memorization, a printed mushaf
should be taken as the reference.
```
