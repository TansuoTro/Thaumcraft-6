/*     */ package thaumcraft.common.lib.utils;
/*     */ 
/*     */ import com.google.common.collect.ImmutableSet;
/*     */ import com.google.common.collect.Sets;
/*     */ import com.google.common.collect.UnmodifiableIterator;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.Set;
/*     */ import net.minecraft.block.properties.IProperty;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class BlockStateUtils
/*     */ {
/*  27 */   public static EnumFacing getFacing(IBlockState state) { return EnumFacing.func_82600_a(state.func_177230_c().func_176201_c(state) & 0x7); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  32 */   public static EnumFacing getFacing(int meta) { return EnumFacing.func_82600_a(meta & 0x7); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  37 */   public static boolean isEnabled(IBlockState state) { return ((state.func_177230_c().func_176201_c(state) & 0x8) != 8); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  42 */   public static boolean isEnabled(int meta) { return ((meta & 0x8) != 8); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static IProperty getPropertyByName(IBlockState blockState, String propertyName) {
/*  48 */     for (UnmodifiableIterator unmodifiableIterator = blockState.func_177228_b().keySet().iterator(); unmodifiableIterator.hasNext(); ) { IProperty property = (IProperty)unmodifiableIterator.next();
/*     */       
/*  50 */       if (property.func_177701_a().equals(propertyName)) return property;
/*     */        }
/*     */     
/*  53 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*  58 */   public static boolean isValidPropertyName(IBlockState blockState, String propertyName) { return (getPropertyByName(blockState, propertyName) != null); }
/*     */ 
/*     */ 
/*     */   
/*     */   public static Comparable getPropertyValueByName(IBlockState blockState, IProperty property, String valueName) {
/*  63 */     for (UnmodifiableIterator unmodifiableIterator = ((ImmutableSet)property.func_177700_c()).iterator(); unmodifiableIterator.hasNext(); ) { Comparable value = (Comparable)unmodifiableIterator.next();
/*     */       
/*  65 */       if (value.toString().equals(valueName)) return value;
/*     */        }
/*     */     
/*  68 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public static ImmutableSet<IBlockState> getValidStatesForProperties(IBlockState baseState, IProperty... properties) {
/*  73 */     if (properties == null) return null;
/*     */     
/*  75 */     Set<IBlockState> validStates = Sets.newHashSet();
/*  76 */     PropertyIndexer propertyIndexer = new PropertyIndexer(properties, null);
/*     */ 
/*     */     
/*     */     do {
/*  80 */       IBlockState currentState = baseState;
/*     */       
/*  82 */       for (IProperty property : properties) {
/*     */         
/*  84 */         IndexedProperty indexedProperty = propertyIndexer.getIndexedProperty(property);
/*     */         
/*  86 */         currentState = currentState.func_177226_a(property, indexedProperty.getCurrentValue());
/*     */       } 
/*     */       
/*  89 */       validStates.add(currentState);
/*     */     }
/*  91 */     while (propertyIndexer.increment());
/*     */     
/*  93 */     return ImmutableSet.copyOf(validStates);
/*     */   }
/*     */   private static class PropertyIndexer { private HashMap<IProperty, BlockStateUtils.IndexedProperty> indexedProperties; private IProperty finalProperty;
/*     */     
/*     */     private PropertyIndexer(IProperty... properties) {
/*  98 */       this.indexedProperties = new HashMap();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 104 */       this.finalProperty = properties[properties.length - 1];
/*     */       
/* 106 */       BlockStateUtils.IndexedProperty previousIndexedProperty = null;
/*     */       
/* 108 */       for (IProperty property : properties) {
/*     */         
/* 110 */         BlockStateUtils.IndexedProperty indexedProperty = new BlockStateUtils.IndexedProperty(property, null);
/*     */         
/* 112 */         if (previousIndexedProperty != null) {
/*     */           
/* 114 */           indexedProperty.parent = previousIndexedProperty;
/* 115 */           previousIndexedProperty.child = indexedProperty;
/*     */         } 
/*     */         
/* 118 */         this.indexedProperties.put(property, indexedProperty);
/* 119 */         previousIndexedProperty = indexedProperty;
/*     */       } 
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 125 */     public boolean increment() { return ((BlockStateUtils.IndexedProperty)this.indexedProperties.get(this.finalProperty)).increment(); }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 130 */     public BlockStateUtils.IndexedProperty getIndexedProperty(IProperty property) { return (BlockStateUtils.IndexedProperty)this.indexedProperties.get(property); } }
/*     */   
/*     */   private static class IndexedProperty { private ArrayList<Comparable> validValues;
/*     */     private int maxCount;
/*     */     
/*     */     private IndexedProperty(IProperty property) {
/* 136 */       this.validValues = new ArrayList();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 146 */       this.validValues.addAll(property.func_177700_c());
/* 147 */       this.maxCount = this.validValues.size() - 1;
/*     */     }
/*     */     private int counter; private IndexedProperty parent; private IndexedProperty child;
/*     */     
/*     */     public boolean increment() {
/* 152 */       if (this.counter < this.maxCount) { this.counter++; }
/*     */       else
/*     */       
/* 155 */       { if (hasParent()) {
/*     */           
/* 157 */           resetSelfAndChildren();
/* 158 */           return this.parent.increment();
/*     */         } 
/* 160 */         return false; }
/*     */ 
/*     */       
/* 163 */       return true;
/*     */     }
/*     */ 
/*     */     
/*     */     public void resetSelfAndChildren() {
/* 168 */       this.counter = 0;
/* 169 */       if (hasChild()) this.child.resetSelfAndChildren();
/*     */     
/*     */     }
/*     */ 
/*     */     
/* 174 */     public boolean hasParent() { return (this.parent != null); }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 179 */     public boolean hasChild() { return (this.child != null); }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 184 */     public int getCounter() { return this.counter; }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 189 */     public int getMaxCount() { return this.maxCount; }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 194 */     public Comparable getCurrentValue() { return (Comparable)this.validValues.get(this.counter); } }
/*     */ 
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\li\\utils\BlockStateUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */