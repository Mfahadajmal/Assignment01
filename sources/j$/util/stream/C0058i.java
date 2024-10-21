package j$.util.stream;

import j$.util.function.BiConsumer$CC;
import j$.util.function.BiFunction$CC;
import j$.util.function.Function$CC;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.function.LongFunction;
import java.util.function.Supplier;

/* renamed from: j$.util.stream.i, reason: case insensitive filesystem */
/* loaded from: classes2.dex */
public final /* synthetic */ class C0058i implements BinaryOperator, Function, Supplier, LongFunction, IntFunction, BiConsumer {
    public final /* synthetic */ int a;

    public /* synthetic */ C0058i(int i) {
        this.a = i;
    }

    @Override // java.util.function.BiConsumer
    public void accept(Object obj, Object obj2) {
        switch (this.a) {
            case 15:
                ((Collection) obj).add(obj2);
                return;
            case 17:
                ((List) obj).add(obj2);
                return;
            case 19:
                ((Set) obj).add(obj2);
                return;
            case 20:
                ((j$.util.O) obj).a((CharSequence) obj2);
                return;
            case 25:
                ((StringBuilder) obj).append((CharSequence) obj2);
                return;
            case 28:
                ((LinkedHashSet) obj).add(obj2);
                return;
            default:
                ((LinkedHashSet) obj).addAll((LinkedHashSet) obj2);
                return;
        }
    }

    public /* synthetic */ BiConsumer andThen(BiConsumer biConsumer) {
        switch (this.a) {
            case 15:
                return BiConsumer$CC.$default$andThen(this, biConsumer);
            case 17:
                return BiConsumer$CC.$default$andThen(this, biConsumer);
            case 19:
                return BiConsumer$CC.$default$andThen(this, biConsumer);
            case 20:
                return BiConsumer$CC.$default$andThen(this, biConsumer);
            case 25:
                return BiConsumer$CC.$default$andThen(this, biConsumer);
            case 28:
                return BiConsumer$CC.$default$andThen(this, biConsumer);
            default:
                return BiConsumer$CC.$default$andThen(this, biConsumer);
        }
    }

    @Override // java.util.function.IntFunction
    public Object apply(int i) {
        return new Object[i];
    }

    public /* synthetic */ Function compose(Function function) {
        switch (this.a) {
            case 3:
                return Function$CC.$default$compose(this, function);
            case 5:
                return Function$CC.$default$compose(this, function);
            case 22:
                return Function$CC.$default$compose(this, function);
            default:
                return Function$CC.$default$compose(this, function);
        }
    }

    @Override // java.util.function.Supplier
    public Object get() {
        switch (this.a) {
            case 8:
                return new Object();
            case 16:
                return new ArrayList();
            case 18:
                return new HashSet();
            case 23:
                return new HashMap();
            case 24:
                return new StringBuilder();
            default:
                return new LinkedHashSet();
        }
    }

    @Override // java.util.function.LongFunction
    public Object apply(long j) {
        switch (this.a) {
            case 9:
                if (j >= 0 && j < 2147483639) {
                    return new V(j);
                }
                return new X();
            default:
                if (j >= 0 && j < 2147483639) {
                    return new Z(j);
                }
                return new C0045b0();
        }
    }

    public /* synthetic */ BiFunction andThen(Function function) {
        switch (this.a) {
            case 0:
                return BiFunction$CC.$default$andThen(this, function);
            case 1:
                return BiFunction$CC.$default$andThen(this, function);
            case 2:
                return BiFunction$CC.$default$andThen(this, function);
            case 3:
            case 5:
            case 8:
            case 9:
            case 11:
            default:
                return BiFunction$CC.$default$andThen(this, function);
            case 4:
                return BiFunction$CC.$default$andThen(this, function);
            case 6:
                return BiFunction$CC.$default$andThen(this, function);
            case 7:
                return BiFunction$CC.$default$andThen(this, function);
            case 10:
                return BiFunction$CC.$default$andThen(this, function);
            case 12:
                return BiFunction$CC.$default$andThen(this, function);
            case 13:
                return BiFunction$CC.$default$andThen(this, function);
        }
    }

    @Override // java.util.function.Function
    public Object apply(Object obj) {
        switch (this.a) {
            case 3:
                Set set = Collectors.a;
                Object[] array = ((Set) obj).toArray();
                HashSet hashSet = new HashSet(array.length);
                for (Object obj2 : array) {
                    obj2.getClass();
                    if (!hashSet.add(obj2)) {
                        throw new IllegalArgumentException("duplicate element: " + obj2);
                    }
                }
                return Collections.unmodifiableSet(hashSet);
            case 5:
                Set set2 = Collectors.a;
                return obj;
            case 22:
                return ((j$.util.O) obj).toString();
            default:
                return ((StringBuilder) obj).toString();
        }
    }

    @Override // java.util.function.BiFunction
    public Object apply(Object obj, Object obj2) {
        switch (this.a) {
            case 0:
                Map map = (Map) obj;
                Collectors.b(map, (Map) obj2);
                return map;
            case 1:
                Collection collection = (Collection) obj;
                Set set = Collectors.a;
                collection.addAll((Collection) obj2);
                return collection;
            case 2:
                Set set2 = (Set) obj;
                Set set3 = (Set) obj2;
                Set set4 = Collectors.a;
                if (set2.size() < set3.size()) {
                    set3.addAll(set2);
                    return set3;
                }
                set2.addAll(set3);
                return set2;
            case 3:
            case 5:
            case 8:
            case 9:
            case 11:
            default:
                j$.util.O o = (j$.util.O) obj;
                o.d((j$.util.O) obj2);
                return o;
            case 4:
                List list = (List) obj;
                Set set5 = Collectors.a;
                list.addAll((List) obj2);
                return list;
            case 6:
                Set set6 = (Set) obj;
                Set set7 = (Set) obj2;
                Set set8 = Collectors.a;
                if (set6.size() < set7.size()) {
                    set7.addAll(set6);
                    return set7;
                }
                set6.addAll(set7);
                return set6;
            case 7:
                StringBuilder sb = (StringBuilder) obj;
                Set set9 = Collectors.a;
                sb.append((CharSequence) obj2);
                return sb;
            case 10:
                return new L((H) obj, (H) obj2);
            case 12:
                return new L((I) obj, (I) obj2);
            case 13:
                return new L((K) obj, (K) obj2);
        }
    }

    /* renamed from: andThen, reason: collision with other method in class */
    public /* synthetic */ Function m247andThen(Function function) {
        switch (this.a) {
            case 3:
                return Function$CC.$default$andThen(this, function);
            case 5:
                return Function$CC.$default$andThen(this, function);
            case 22:
                return Function$CC.$default$andThen(this, function);
            default:
                return Function$CC.$default$andThen(this, function);
        }
    }
}
