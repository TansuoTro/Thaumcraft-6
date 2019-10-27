/*     */ package thaumcraft.proxies;
/*     */ 
/*     */ import net.minecraft.client.multiplayer.WorldClient;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.util.math.RayTraceResult;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.WorldServer;
/*     */ import thaumcraft.api.golems.seals.ISealEntity;
/*     */ import thaumcraft.client.gui.GuiArcaneBore;
/*     */ import thaumcraft.client.gui.GuiArcaneWorkbench;
/*     */ import thaumcraft.client.gui.GuiFocalManipulator;
/*     */ import thaumcraft.client.gui.GuiFocusPouch;
/*     */ import thaumcraft.client.gui.GuiGolemBuilder;
/*     */ import thaumcraft.client.gui.GuiHandMirror;
/*     */ import thaumcraft.client.gui.GuiLogistics;
/*     */ import thaumcraft.client.gui.GuiPech;
/*     */ import thaumcraft.client.gui.GuiPotionSprayer;
/*     */ import thaumcraft.client.gui.GuiResearchBrowser;
/*     */ import thaumcraft.client.gui.GuiResearchTable;
/*     */ import thaumcraft.client.gui.GuiSmelter;
/*     */ import thaumcraft.client.gui.GuiSpa;
/*     */ import thaumcraft.client.gui.GuiThaumatorium;
/*     */ import thaumcraft.client.gui.GuiTurretAdvanced;
/*     */ import thaumcraft.client.gui.GuiTurretBasic;
/*     */ import thaumcraft.client.gui.GuiVoidSiphon;
/*     */ import thaumcraft.codechicken.lib.raytracer.RayTracer;
/*     */ import thaumcraft.common.container.ContainerArcaneBore;
/*     */ import thaumcraft.common.container.ContainerArcaneWorkbench;
/*     */ import thaumcraft.common.container.ContainerFocalManipulator;
/*     */ import thaumcraft.common.container.ContainerFocusPouch;
/*     */ import thaumcraft.common.container.ContainerGolemBuilder;
/*     */ import thaumcraft.common.container.ContainerHandMirror;
/*     */ import thaumcraft.common.container.ContainerLogistics;
/*     */ import thaumcraft.common.container.ContainerPech;
/*     */ import thaumcraft.common.container.ContainerPotionSprayer;
/*     */ import thaumcraft.common.container.ContainerResearchTable;
/*     */ import thaumcraft.common.container.ContainerSmelter;
/*     */ import thaumcraft.common.container.ContainerSpa;
/*     */ import thaumcraft.common.container.ContainerThaumatorium;
/*     */ import thaumcraft.common.container.ContainerTurretAdvanced;
/*     */ import thaumcraft.common.container.ContainerTurretBasic;
/*     */ import thaumcraft.common.container.ContainerVoidSiphon;
/*     */ import thaumcraft.common.entities.construct.EntityArcaneBore;
/*     */ import thaumcraft.common.entities.construct.EntityTurretCrossbow;
/*     */ import thaumcraft.common.entities.construct.EntityTurretCrossbowAdvanced;
/*     */ import thaumcraft.common.entities.monster.EntityPech;
/*     */ import thaumcraft.common.golems.ItemGolemBell;
/*     */ import thaumcraft.common.tiles.crafting.TileArcaneWorkbench;
/*     */ import thaumcraft.common.tiles.crafting.TileFocalManipulator;
/*     */ import thaumcraft.common.tiles.crafting.TileGolemBuilder;
/*     */ import thaumcraft.common.tiles.crafting.TileResearchTable;
/*     */ import thaumcraft.common.tiles.crafting.TileThaumatorium;
/*     */ import thaumcraft.common.tiles.crafting.TileVoidSiphon;
/*     */ import thaumcraft.common.tiles.devices.TilePotionSprayer;
/*     */ import thaumcraft.common.tiles.devices.TileSpa;
/*     */ import thaumcraft.common.tiles.essentia.TileSmelter;
/*     */ 
/*     */ 
/*     */ public class ProxyGUI
/*     */ {
/*     */   public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
/*  64 */     if (world instanceof WorldClient) {
/*  65 */       EnumFacing side; BlockPos target; RayTraceResult ray; ISealEntity se; switch (ID) { case 13:
/*  66 */           return new GuiArcaneWorkbench(player.field_71071_by, (TileArcaneWorkbench)world.func_175625_s(new BlockPos(x, y, z)));
/*  67 */         case 12: return new GuiResearchBrowser();
/*  68 */         case 10: return new GuiResearchTable(player, (TileResearchTable)world.func_175625_s(new BlockPos(x, y, z)));
/*  69 */         case 9: return new GuiSmelter(player.field_71071_by, (TileSmelter)world.func_175625_s(new BlockPos(x, y, z)));
/*  70 */         case 1: return new GuiPech(player.field_71071_by, world, (EntityPech)((WorldClient)world).func_73045_a(x));
/*     */         case 16:
/*  72 */           return new GuiTurretBasic(player.field_71071_by, world, (EntityTurretCrossbow)((WorldClient)world).func_73045_a(x));
/*  73 */         case 17: return new GuiTurretAdvanced(player.field_71071_by, world, (EntityTurretCrossbowAdvanced)((WorldClient)world).func_73045_a(x));
/*  74 */         case 3: return new GuiThaumatorium(player.field_71071_by, (TileThaumatorium)world.func_175625_s(new BlockPos(x, y, z)));
/*  75 */         case 14: return new GuiArcaneBore(player.field_71071_by, world, (EntityArcaneBore)((WorldClient)world).func_73045_a(x));
/*  76 */         case 4: return new GuiHandMirror(player.field_71071_by, world, x, y, z);
/*  77 */         case 5: return new GuiFocusPouch(player.field_71071_by, world, x, y, z);
/*  78 */         case 6: return new GuiSpa(player.field_71071_by, (TileSpa)world.func_175625_s(new BlockPos(x, y, z)));
/*  79 */         case 7: return new GuiFocalManipulator(player.field_71071_by, (TileFocalManipulator)world.func_175625_s(new BlockPos(x, y, z)));
/*  80 */         case 19: return new GuiGolemBuilder(player.field_71071_by, (TileGolemBuilder)world.func_175625_s(new BlockPos(x, y, z)));
/*  81 */         case 21: return new GuiPotionSprayer(player.field_71071_by, (TilePotionSprayer)world.func_175625_s(new BlockPos(x, y, z)));
/*  82 */         case 22: return new GuiVoidSiphon(player.field_71071_by, (TileVoidSiphon)world.func_175625_s(new BlockPos(x, y, z)));
/*     */         case 18:
/*  84 */           se = ItemGolemBell.getSeal(player);
/*  85 */           if (se != null) return se.getSeal().returnGui(world, player, new BlockPos(x, y, z), (se.getSealPos()).face, se); 
/*     */           break;
/*     */         case 20:
/*  88 */           ray = RayTracer.retrace(player);
/*  89 */           target = null;
/*  90 */           side = null;
/*  91 */           if (ray != null && ray.field_72313_a == RayTraceResult.Type.BLOCK) {
/*  92 */             target = ray.func_178782_a();
/*  93 */             side = ray.field_178784_b;
/*     */           } 
/*  95 */           return new GuiLogistics(player.field_71071_by, world, target, side); }
/*     */     
/*     */     } 
/*  98 */     return null;
/*     */   }
/*     */   public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
/*     */     ISealEntity se;
/* 102 */     switch (ID) { case 13:
/* 103 */         return new ContainerArcaneWorkbench(player.field_71071_by, (TileArcaneWorkbench)world.func_175625_s(new BlockPos(x, y, z)));
/* 104 */       case 10: return new ContainerResearchTable(player.field_71071_by, (TileResearchTable)world.func_175625_s(new BlockPos(x, y, z)));
/* 105 */       case 9: return new ContainerSmelter(player.field_71071_by, (TileSmelter)world.func_175625_s(new BlockPos(x, y, z)));
/* 106 */       case 1: return new ContainerPech(player.field_71071_by, world, (EntityPech)((WorldServer)world).func_73045_a(x));
/*     */       case 16:
/* 108 */         return new ContainerTurretBasic(player.field_71071_by, world, (EntityTurretCrossbow)((WorldServer)world).func_73045_a(x));
/* 109 */       case 17: return new ContainerTurretAdvanced(player.field_71071_by, world, (EntityTurretCrossbowAdvanced)((WorldServer)world).func_73045_a(x));
/* 110 */       case 3: return new ContainerThaumatorium(player.field_71071_by, (TileThaumatorium)world.func_175625_s(new BlockPos(x, y, z)));case 5: return new ContainerFocusPouch(player.field_71071_by, world, x, y, z);
/*     */       case 14:
/* 112 */         return new ContainerArcaneBore(player.field_71071_by, world, (EntityArcaneBore)((WorldServer)world).func_73045_a(x));
/* 113 */       case 4: return new ContainerHandMirror(player.field_71071_by, world, x, y, z);
/* 114 */       case 6: return new ContainerSpa(player.field_71071_by, (TileSpa)world.func_175625_s(new BlockPos(x, y, z)));
/* 115 */       case 7: return new ContainerFocalManipulator(player.field_71071_by, (TileFocalManipulator)world.func_175625_s(new BlockPos(x, y, z)));
/* 116 */       case 19: return new ContainerGolemBuilder(player.field_71071_by, (TileGolemBuilder)world.func_175625_s(new BlockPos(x, y, z)));
/* 117 */       case 21: return new ContainerPotionSprayer(player.field_71071_by, (TilePotionSprayer)world.func_175625_s(new BlockPos(x, y, z)));
/* 118 */       case 22: return new ContainerVoidSiphon(player.field_71071_by, (TileVoidSiphon)world.func_175625_s(new BlockPos(x, y, z)));
/*     */       case 18:
/* 120 */         se = ItemGolemBell.getSeal(player);
/* 121 */         if (se != null) return se.getSeal().returnContainer(world, player, new BlockPos(x, y, z), (se.getSealPos()).face, se); 
/*     */         break;
/*     */       case 20:
/* 124 */         return new ContainerLogistics(player.field_71071_by, world); }
/*     */     
/* 126 */     return null;
/*     */   }
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\proxies\ProxyGUI.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */