package org.example;

import org.apache.commons.lang3.tuple.Pair;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws IOException {
        long time = System.currentTimeMillis();
        List<Path> files;
        Path source = Path.of("src/input");
        try (Stream<Path> stream = Files.list(source)){
        files = stream.collect(Collectors.toList());

        ForkJoinPool pool = new ForkJoinPool(4);
        pool.submit( () -> {
                    Stream<Path> stream2 = files.parallelStream();
                    Stream<Pair<String, BufferedImage>> pairStream = stream2.map(value -> {
                        try {
                            Pair<String, BufferedImage> pair1;
                            pair1 = Pair.of(value.toString().substring(10), ImageIO.read(value.toFile()));
                            return pair1;
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        return null;
                    });

        pairStream.parallel();
        pairStream.forEach(p -> { if(p.getValue()!= null) {
            //System.out.println(Thread.currentThread());
            BufferedImage original = p.getValue();
            BufferedImage image = new BufferedImage(original.getWidth(),
                    original.getHeight(),
                    original.getType());
            for (int i = 0; i < original.getWidth(); i++) {
                for (int j = 0; j < original.getHeight(); j++) {
                    int rgb = original.getRGB(i, j);
                    Color color = new Color(rgb);
                    int red = (int) (color.getRed() * 0.299);
                    int green = (int) (color.getGreen() * 0.587);
                    int blue = (int) (color.getBlue() * 0.114);
                    Color outColor = new Color(red, green, blue);
                    int outRgb = outColor.getRGB();
                    image.setRGB(i, j, outRgb);
                }
            }
            //System.out.println(p.getKey());
            String newPath = "src/output/" + p.getKey();
            File outputfile = new File(newPath);
            try {
                ImageIO.write(image, "jpg", outputfile);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        });
        }).get();
        pool.shutdown();
        } catch (IOException e){
            System.out.println("error");
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(System.currentTimeMillis() - time);
    }
}
