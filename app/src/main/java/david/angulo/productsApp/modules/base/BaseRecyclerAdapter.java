package david.angulo.productsApp.modules.base;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;


public abstract class BaseRecyclerAdapter<E, VH extends BaseRecyclerAdapter.ViewHolder> extends RecyclerView.Adapter<VH> {
    public String LOG_TAG = "";
    private List<E> list;
    private LayoutInflater inflater;

    public BaseRecyclerAdapter() {
        list = new ArrayList<>();
        LOG_TAG = this.getClass().getSimpleName();
    }

    public BaseRecyclerAdapter(List<E> list) {
        if (list == null) {
            list = new ArrayList<>();
        }
        this.list = list;
    }

    @Override
    public final void onBindViewHolder(VH holder, int position) {
        onBindViewHolder(holder, position, getItem(position));
    }

    public void onBindViewHolder(VH holder, int position, E item) {
        holder.bind(position);
    }

    // public abstract void onBindViewHolder(VH holder, int position, E item);

    public int getCount() {
        return getItemCount();
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public E getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return list.get(position).hashCode();
    }

    public void setList(List<E> newList) {
        if (newList == null) {
            newList = new ArrayList<>();
        }
        this.list = newList;
        notifyDataSetChanged();
    }

    public List<E> getList() {
        return list;
    }

    public void addAll(List<E> addList) {
        if (addList == null || addList.size() == 0) {
            return;
        }
        int currentLastIndex = list.size() - 1;
        this.list.addAll(addList);
        notifyItemRangeInserted(currentLastIndex, addList.size());
    }

    public void addAll(int position, List<E> addList) {
        if (addList == null || addList.size() == 0) {
            return;
        }
        this.list.addAll(position, addList);
        notifyItemRangeInserted(position, addList.size());
    }

    public void remove(int position) {
        if (position < 0) {
            return;
        }
        int lastPosition = this.list.size() - 1;
        if (position > lastPosition) {
            return;
        }
        this.list.remove(position);
        notifyItemRemoved(position);
    }

    public void remove(E element) {
        int position = this.list.indexOf(element);
        remove(position);
    }

    public void removeAll(int positionStart, int count) {
        if (positionStart < 0) {
            return;
        }
        if (count <= 0) {
            return;
        }
        int lastPosition = this.list.size() - 1;
        if (positionStart > lastPosition) {
            return;
        }

        if (lastPosition > positionStart + count) {
            lastPosition = positionStart + count;
        }

        List<E> removeList = this.list.subList(positionStart, lastPosition);
        this.list.removeAll(removeList);
        notifyItemRangeRemoved(positionStart, lastPosition - positionStart);
    }

    public void removeAll(List<E> elementList) {
        if (elementList == null || elementList.size() == 0) {
            return;
        }
        this.list.removeAll(elementList);
        notifyDataSetChanged();
    }

    public void removeAll() {
        this.list.clear();
        notifyDataSetChanged();
    }

    public void update(int position, E newElement) {
        if (position < 0 || newElement == null || position > list.size() - 1) {
            return;
        }
        this.list.set(position, newElement);
        notifyItemChanged(position);
    }

    protected View inflate(ViewGroup parent, int layoutId) {
        return getInflater(parent.getContext()).inflate(layoutId, parent, false);
    }

    protected LayoutInflater getInflater(Context context) {
        if (inflater == null) {
            inflater = LayoutInflater.from(context);
        }
        return inflater;
    }

    public static abstract class ViewHolder extends RecyclerView.ViewHolder {
        public abstract void bind(int position);

        // fun bind(position: Int) {
        public ViewHolder(View itemView) {
            super(itemView);
        }
    }

}