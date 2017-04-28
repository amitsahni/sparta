package android.base.fragment;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.support.v4.app.FragmentActivity;


/**
 * The type Frag param.
 */
public class FragParam {

    /**
     * The Context.
     */
    protected FragmentActivity context;
    /**
     * The Replace id.
     */
    protected int replaceId, /**
     * The Enter.
     */
    enter = 0, /**
     * The Exit.
     */
    exit = 0, /**
     * The Pop enter.
     */
    popEnter = 0, /**
     * The Pop exit.
     */
    popExit = 0;
    /**
     * The Fragment.
     */
    protected Fragment fragment;
    /**
     * The Tag.
     */
    protected String tag;
    /**
     * The Frag type.
     */
    protected FragType fragType = FragType.REPLACE;

    /**
     * The Enable animation.
     */
    protected boolean enableAnimation = false, /**
     * The Is back stack.
     */
    isBackStack = false;

    /**
     * The enum Frag type.
     */
    public enum FragType {
        /**
         * The Replace.
         */
        REPLACE {
            @Override
            public void execute(FragParam fragParam, FragmentTransaction ft) {
                FragmentManagerUtil.replace(fragParam, ft);
            }
        }, /**
         * The Pop.
         */
        POP {
            @Override
            public void execute(FragParam fragParam, FragmentTransaction ft) {
                FragmentManagerUtil.pop(fragParam, ft);
            }
        }, /**
         * The Pop tag.
         */
        POP_TAG {
            @Override
            public void execute(FragParam fragParam, FragmentTransaction ft) {
                FragmentManagerUtil.popTag(fragParam, ft);
            }
        }, /**
         * The Restart.
         */
        RESTART {
            @Override
            public void execute(FragParam fragParam, FragmentTransaction ft) {
                FragmentManagerUtil.restart(fragParam, ft);
            }
        }, /**
         * The Clear.
         */
        CLEAR {
            @Override
            public void execute(FragParam fragParam, FragmentTransaction ft) {
                FragmentManagerUtil.clear(fragParam);
            }
        };

        /**
         * Execute.
         *
         * @param fragParam the frag param
         * @param ft        the ft
         */
        public abstract void execute(FragParam fragParam, FragmentTransaction ft);
    }
}
