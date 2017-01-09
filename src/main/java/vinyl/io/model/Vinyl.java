package vinyl.io.model;

import java.util.Arrays;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

/**
 * @author ADURAN
 *
 */
@Entity
public class Vinyl  {

    /**
     * 
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    /**
     * 
     */
    @Column(name = "title")
    private String title;
    /**
     * 
     */
    @Column(name = "artist")
    private String artist;
    /**
     * 
     */ 
    @Column(name = "edited")
    private String edited;
       
    /**
     * 
     */
    @OneToOne 
    @JoinColumn(name="genre1_id")
    private Genre genre1;
    
    /**
     * 
     */
    @OneToOne 
    @JoinColumn(
            name="genre2_id")
    private Genre genre2;
    
    /**
     * 
     */
    @Lob
    @Column(name = "imageFront", length = 100000)
    private byte[] imageFront;
    /**
     * 
     */
    @Lob
    @Column(name = "imageBack", length = 100000)
    private byte[] imageBack;

    /**
     * 
     */
    @Transient
    private String imageSrcFront;

    /**
     * 
     */
    @Transient
    private String imageSrcBack;

    /**
     * @return the id
     */

    public Integer getId() {
        return this.id;
    }

    /**
     * @param id
     *            the id to set
     */

    public void setId(final Integer id) {
        this.id = id;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return this.title;
    }
    
    
	public Genre getGenre1() {
		return genre1;
	}

	public void setGenre1(Genre genre1) {
		this.genre1 = genre1;
	}

	public Genre getGenre2() {
		return genre2;
	}

	public void setGenre2(Genre genre2) {
		this.genre2 = genre2;
	}

	/**
     * @param title
     *            the title to set
     */
    public void setTitle(final String title) {
        this.title = title;
    }

    /**
     * @return the imageBack
     */
    public byte[] getImageBack() {
        return this.imageBack == null ? "/images/img_not_available.png".getBytes() : this.imageBack;
    }

    /**
     * @param imageBack
     *            the imageBack to set
     */
    public void setImageBack(final byte[] imageBack) {
        this.imageBack = imageBack;
    }

    /**
     * @return the imageSrcBack
     */
    public String getImageSrcBack() {
        return this.imageSrcBack == null ? new String(this.getImageBack()) : this.imageSrcBack;
    }

    /**
     * @param imageSrcBack
     *            the imageSrcBack to set
     */
    public void setImageSrcBack(final String imageSrcBack) {
        this.imageSrcBack = imageSrcBack;
    }

    /**
     * @return the imageFront
     */

    public byte[] getImageFront() {
        return this.imageFront == null ? "/images/img_not_available.png".getBytes() : this.imageFront;
    }

    /**
     * @param imageFront
     *            the imageFront to set
     */
    public void setImageFront(final byte[] imageFront) {
        this.imageFront = imageFront;
    }

    /**
     * @return the edited
     */
 
    public String getEdited() {
        return this.edited;
    }

    /**
     * @param edited
     *            the edited to set
     */
  
    public void setEdited(final String edited) {
        this.edited = edited;
    }

    /**
     * @return the artist
     */

    public String getArtist() {
        return this.artist;
    }

    /**
     * @param artist
     *            the artist to set
     */

    public void setArtist(final String artist) {
        this.artist = artist;
    }

    /**
     * @return the imageSrcFront
     */
  
    public String getImageSrcFront() {
        return this.imageSrcFront == null ? new String(this.getImageFront()) : this.imageSrcFront;
    }

    /**
     * @param imageSrcFront
     *            the imageSrcFront to set
     */

    public void setImageSrcFront(final String imageSrcFront) {
        this.imageSrcFront = imageSrcFront;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
	public String toString() {
		return "Vinyl [id=" + id + ", title=" + title + ", artist=" + artist + ", edited=" + edited + ", genre1="
				+ genre1 + ", genre2=" + genre2 + ", imageFront=" + Arrays.toString(imageFront) + ", imageBack="
				+ Arrays.toString(imageBack) + ", imageSrcFront=" + imageSrcFront + ", imageSrcBack=" + imageSrcBack
				+ "]";
	}   

	/**
	 * @param id
	 * @param title
	 * @param artist
	 * @param edited
	 * @param genre1
	 * @param genre2
	 */
	public Vinyl(Integer id, String title, String artist, String edited, Genre genre1, Genre genre2) {
		super();
		this.id = id;
		this.title = title;
		this.artist = artist;
		this.edited = edited;
		this.genre1 = genre1;
		this.genre2 = genre2;
	}

	/**
     * 
     */
    public Vinyl() {
        super();
        // TODO Auto-generated constructor stub
    }

}
