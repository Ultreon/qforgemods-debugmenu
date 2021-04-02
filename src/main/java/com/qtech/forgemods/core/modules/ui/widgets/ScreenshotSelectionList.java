package com.qtech.forgemods.core.modules.ui.widgets;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import com.qtech.forgemods.core.common.FloatSize;
import com.qtech.forgemods.core.modules.ui.common.Resizer;
import com.qtech.forgemods.core.modules.ui.common.Screenshot;
import com.qtech.forgemods.core.modules.ui.screens.ScreenshotsScreen;
import lombok.Getter;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.widget.list.ExtendedList;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Util;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.io.File;
import java.util.List;
import java.util.Optional;

@OnlyIn(Dist.CLIENT)
public class ScreenshotSelectionList extends ExtendedList<ScreenshotSelectionList.Entry> {
   private static final ResourceLocation UNKNOWN_IMAGE = new ResourceLocation("textures/misc/unknown_server.png");
   private final ScreenshotsScreen gui;
   private List<Screenshot> screenshots;

   public ScreenshotSelectionList(ScreenshotsScreen gui, Minecraft minecraft, int widthIn, int heightIn, int topIn, int bottomIn, @Nullable ScreenshotSelectionList parent) {
      super(minecraft, widthIn, heightIn, topIn, bottomIn, 26);
      this.gui = gui;

      if (parent != null) {
         this.screenshots = parent.screenshots;
      }

      this.x0 = 10;
      this.x1 = 210;
   }

   public void loadScreenshots() {
      this.clearEntries();

      if (this.screenshots == null) {
         this.screenshots = this.gui.getScreenshots();
      }

      List<Screenshot> screenshotList = this.screenshots;
      for (int i = 0; i < screenshotList.size(); i++) {
         Screenshot screenshot = screenshotList.get(i);
         this.addScreenshot(screenshot, i);
//         this.addEntry(new Entry(this, screenshot, i));
      }
   }

   public void addScreenshot(Screenshot screenshot, int index) {
      this.addEntry(new Entry(this, screenshot, index));
   }

   @Override
   public int addEntry(@NotNull ScreenshotSelectionList.Entry entry) {
      return super.addEntry(entry);
   }

   @Override
   protected int getScrollbarPosition() {
      return 200;
   }

   @Override
   public int getRowWidth() {
      return 200;
   }

   @Override
   public int getRowLeft() {
      return 12;
   }

   @Override
   protected boolean isFocused() {
      return this.gui.getListener() == this;
   }

   public Optional<ScreenshotSelectionList.Entry> func_214376_a() {
      return Optional.ofNullable(this.getSelected());
   }

   public ScreenshotsScreen getGuiScreenshots() {
      return this.gui;
   }

   @OnlyIn(Dist.CLIENT)
   public final class Entry extends ExtendedList.AbstractListEntry<ScreenshotSelectionList.Entry> implements AutoCloseable {
      private final Minecraft minecraft;
      private final ScreenshotsScreen gui;
      @Getter private final int index;
      @Getter private final Screenshot screenshot;
      private final DynamicTexture texture;
      private final ResourceLocation textureLocation;
      private File file;
      private long lastClick;

      public Entry(ScreenshotSelectionList list, Screenshot screenshot, int index) {
         this.gui = list.getGuiScreenshots();
         this.index = index;
         this.minecraft = Minecraft.getInstance();
         this.file = screenshot.getFile();
         if (!this.file.isFile()) {
            this.file = null;
         }

         this.screenshot = screenshot;
         this.texture = this.screenshot.getTexture();
         this.textureLocation = this.screenshot.getResourceLocation();
      }

      @SuppressWarnings("deprecation")
      @Override
      public void render(@NotNull MatrixStack matrixStack, int p_230432_2_, int scroll, int xOffset, int p_230432_5_, int p_230432_6_, int p_230432_7_, int p_230432_8_, boolean p_230432_9_, float p_230432_10_) {
         String name = this.file.getName();
         String size;

         if (this.texture != null && this.texture.getTextureData() != null) {
            size = this.texture.getTextureData().getWidth() + "x" + this.texture.getTextureData().getHeight();
         } else {
            size = "Invalid screenshot.";
         }

         StringTextComponent description = new StringTextComponent("");

         this.minecraft.fontRenderer.drawString(matrixStack, name, (float)(xOffset + 32 + 3), (float)(scroll + 1), 0xffffff);
         this.minecraft.fontRenderer.drawString(matrixStack, size, (float)(xOffset + 32 + 3), (float)(scroll + 9 + 3), 0x808080);
         this.minecraft.fontRenderer.func_243248_b(matrixStack, description, (float)(xOffset + 32 + 3), (float)(scroll + 9 + 9 + 3), 0x808080);
         RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
         this.minecraft.getTextureManager().bindTexture(this.texture != null ? this.textureLocation : ScreenshotSelectionList.UNKNOWN_IMAGE);
         RenderSystem.enableBlend();
//         AbstractGui.blit(p_230432_1_, p_230432_4_, p_230432_3_, 0.0F, 0.0F, 32, 32, 32, 32);

         if (this.texture != null) {
            int imgWidth = this.texture.getTextureData().getWidth();
            int imgHeight = this.texture.getTextureData().getHeight();

            Resizer resizer = new Resizer(imgWidth, imgHeight);
            FloatSize size1 = resizer.thumbnail(32f, 22f);

            int width = (int) size1.width;
            int height = (int) size1.height;

            blit(matrixStack, xOffset, scroll, 0f, 0f, width, height, width, height);
         } else {
            blit(matrixStack, xOffset, scroll, 0f, 0f, 32, 32, 32, 32);
         }

         RenderSystem.disableBlend();
//         if (this.minecraft.gameSettings.touchscreen || p_230432_9_) {
//            this.minecraft.getTextureManager().bindTexture(ScreenshotSelectionList.field_214379_d);
////            AbstractGui.fill(p_230432_1_, p_230432_4_, p_230432_3_, p_230432_4_ + 32, p_230432_3_ + 32, -1601138544);
//            RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
//            int i = p_230432_7_ - p_230432_4_;
//            boolean flag = i < 32;
//            int j = flag ? 32 : 0;
//            if (this.field_214451_d.isLocked()) {
//               AbstractGui.blit(p_230432_1_, p_230432_4_, p_230432_3_, 96.0F, (float)j, 32, 32, 256, 256);
//               if (flag) {
//                  this.gui.func_239026_b_(this.minecraft.fontRenderer.trimStringToWidth(ScreenshotSelectionList.field_243466_v, 175));
//               }
//            } else if (this.field_214451_d.markVersionInList()) {
//               AbstractGui.blit(p_230432_1_, p_230432_4_, p_230432_3_, 32.0F, (float)j, 32, 32, 256, 256);
//               if (this.field_214451_d.askToOpenWorld()) {
//                  AbstractGui.blit(p_230432_1_, p_230432_4_, p_230432_3_, 96.0F, (float)j, 32, 32, 256, 256);
//                  if (flag) {
//                     this.gui.func_239026_b_(ImmutableList.of(ScreenshotSelectionList.field_243462_r.func_241878_f(), ScreenshotSelectionList.field_243463_s.func_241878_f()));
//                  }
//               } else if (!SharedConstants.getVersion().isStable()) {
//                  AbstractGui.blit(p_230432_1_, p_230432_4_, p_230432_3_, 64.0F, (float)j, 32, 32, 256, 256);
//                  if (flag) {
//                     this.gui.func_239026_b_(ImmutableList.of(ScreenshotSelectionList.field_243464_t.func_241878_f(), ScreenshotSelectionList.field_243465_u.func_241878_f()));
//                  }
//               }
//            } else {
//               AbstractGui.blit(p_230432_1_, p_230432_4_, p_230432_3_, 0.0F, (float)j, 32, 32, 256, 256);
//            }
//         }
      }

      @Override
      public boolean mouseClicked(double mouseX, double mouseY, int button) {
         ScreenshotSelectionList.this.setSelected(this);
         this.gui.refresh();
         if (mouseX - (double) ScreenshotSelectionList.this.getRowLeft() <= 32.0D) {
//            this.openScreenshot();
            return true;
         } else if (Util.milliTime() - this.lastClick < 250L) {
//            this.openScreenshot();
            return true;
         } else {
            this.lastClick = Util.milliTime();
            return false;
         }
      }

      public void close() {

      }
   }
}
